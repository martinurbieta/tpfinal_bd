<?php
class CurlPost 
{
	protected $url;
	static function init ($url, $method) {
		return new self($url, $method);
	}
	function __construct ($url, $method) {
		$this->url = $url;
		$this->method = $method;
	}
	function send ($request) {
		$ch = curl_init( $this->url );
		curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($request) );
		curl_setopt( $ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
		curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
		curl_setopt($ch, CURLOPT_URL, $this->url);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $this->method);
		$result = curl_exec($ch);
		curl_close($ch);
		return new CurlResponse($this->url, $request, $result);
	}
}
class JsonLoader 
{
	protected $file='tpfinaldata.json';
	protected $data;
	static function load ($file) {
		return new self($file);
	}
	function __construct ($file='') {
		if ($file)
			$this->file = $file;
		if (!is_file($file))
			throw new Exception('No existe el archivo '. $file.'. No se pudo cargar');
		$this->data = file_get_contents($file);
	}
	function decode() {
		return json_decode ($this->data);
	}
	function file() {
		return $this->file;
	}

}
class CurlResponse 
{
	protected $body;
	protected $url;
	protected $request;
	public function __construct ($url, $request, $response) {
		$this->url = $url;
		$this->request = $request;
		$this->body = $this->checkStatus($response);
	}
	public function checkStatus ($content) {
		$response = json_decode($content);
		if (isset($response->status) && is_numeric($response->status) && $response->status >= 400) {
			AppCurlPostOutput::getInstance()->saveFile('processed.json');
			throw new Exception('Error al enviar '. json_encode($this->request, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES). ' a la direccion '.$this->url);
		}
		return $response;
	}
 	public function url() {
 		return $this->url;
	}
 	public function request() {
 		return $this->request;
	}
 	public function decode() {
		return $this->body;
	}

}
class AppCurlPostOutput {
	static protected $instance;
	protected $content=[];
	static function getInstance () {
		if (!self::$instance instanceof self) {
            self::$instance = new self();
        }
        return self::$instance;
	}
	function __construct () {
	}
	function add ($class, CurlResponse $response) {
		if (!isset($this->content[$class])) $this->content[$class] = [];
		$this->content[$class][] = ['url'=>$response->url(), 'request'=>$response->request(), 'response'=>$response->decode()];
	}
	function clear () {
		$this->content = [];
	}
	function printR () {
		print_r($this->content);
	}
	function printJson() {
		echo json_encode($this->content, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES);
	}
	function saveFile($output='output.json') {
		file_put_contents($output, json_encode($this->content, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));
	}
}
class EntityTracer {
	protected $keys;
	function __construct ($keys) {
		$this->keys = $keys;
	}
	public function addKey ($name, $key) {
		$this->keys[$name][] = $key;
	}
	public function findEntitiesIdNames ($items) {
		return array_intersect(array_keys((array) $items), array_keys($this->keys));
	}
	public function trace($entitiesNames, $entities) {
		foreach ($entitiesNames as $name) {
			if (is_iterable($entities->$name)) 
				$this->recursive($name, $entities);
			else 
				$this->complete($name, $entities);
		}
		return $entities;
	}
	private function recursive($name, &$entities) {
		if ($name == 'items') {
			foreach ($entities->$name as &$items) {
				$childrens = $this->findEntitiesIdNames($items);
				$this->trace($childrens, $items);
			}
		} else {
			foreach ($entities->$name as &$entity) 
				$entity->id = $this->keys[$name][$entity->id-1];
		}		
		return $entities->$name;
	}
	private function complete($name, &$entities) {
		//Entra cuando es una asociacion de una propiedad que se relaciona con una entidad
		if (is_string($this->keys[$name])) 
			$entities->$name = $this->keys[$this->keys[$name]][$entities->$name-1];
		//Entra cuando es una entidad con un id que se debe recuperar
		elseif (isset($entities->$name->id)) 
			$entities->$name->id = $this->keys[$name][$entities->$name->id-1];
		return $entities->$name;
	}
	public function printR () {
		print_r($this->keys);
	}
	public function printJson() {
		echo json_encode($this->keys, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES);
	}
	public function saveFile($output='keys.json') {
		file_put_contents($output, json_encode($this->keys, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES));
	}
}
class AppCurlPostClient {
	protected $json;
	protected $baseurl;
	protected $output;
	protected $tracker;
	static function start ($config) {
		return new self($config);
	}
	function __construct ($config) {
		$this->baseurl = isset($config['baseurl'])?$config['baseurl']: 'http://localhost:8081/api';
		$this->json = $config['json']->decode();
		$this->output = AppCurlPostOutput::getInstance();
		$method = explode('.', $config['json']->file());
		$this->method = strtoupper($method[0]);
		$this->isValidMethod($this->method);
		$this->tracker = new EntityTracer($this->getKeys($config['fileKeys']));
	}
	function getKeys($fileKeys) {
		if (!$fileKeys)
			return ['items'=>true, 'id_supplier'=>'supplier', 'client_address'=>'address'];
		else
			return json_decode(file_get_contents($fileKeys), true);
	}
	function isValidMethod($method) {
		if (!$method || !in_array($method,['PUT', 'POST', 'GET', 'DELETE'])) 
			throw new Exception("El archivo debe tener en su nombre el metodo del http, ej: post.nombrearchivo.json");
		return true;		
	}
	function renderUrl ($action) {
		$pieces = explode('/', $action);
		foreach ($pieces as $piece) {
			if (strpos($piece,':') !== false) {
				list($name, $id) = explode(':', $piece);
				$traced = $this->tracker->trace([$name], json_decode('{"'.$name.'" : { "id" :'.$id.'} }'));
				$piece = $traced->$name->id;
			}
			$url[] = $piece;
		}
		return $this->baseurl.'/'.implode('/', $url);
	}
	function keys () {
		return $this->tracker;
	}
	function execute () {
		foreach ($this->json as $name => $array) {
			foreach ($array as $request) {
				$names = $this->tracker->findEntitiesIdNames($request);
				if ($names) 
					$this->tracker->trace($names, $request);
				$url = $this->renderUrl($name);
				$curlresponse = CurlPost::init($url, $this->method)->send($request);
				$entity = $curlresponse->decode();
				//Las ordenes tenian number y no id
				$id = isset($entity->id)?$entity->id:(isset($entity->number)?$entity->number:null);
				if ($id) 
					$this->tracker->addKey($name, $id);

				$this->output->add($name, $curlresponse);
			}
		}
		return $this->output;
	}
}
$args = getopt("i:k:u:o:s::");

$config = ['json'=>JsonLoader::load($args['i'])]; //Recupera la información del json
if (isset($args['u']))
	$config['baseurl'] = $args['u']; //Url base
$config['fileKeys'] = isset($args['k'])?$args['k']:null; //Nombre de archivo de las claves guardadas

$app = AppCurlPostClient::start($config);

$app->execute()->saveFile(isset($args['o'])?$args['o']:null);

if (isset($args['s'])) {
	$app->keys()->saveFile(isset($args['o'])?'keys.'.$args['o']:null);
}
