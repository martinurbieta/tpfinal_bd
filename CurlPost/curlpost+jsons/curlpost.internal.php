<?php
class CurlPost 
{
	protected $url;
	static function init ($url) {
		return new self($url);
	}
	function __construct ($url) {
		$this->url = $url;
	}
	function send ($payload) {
		$ch = curl_init( $this->url );
		curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($payload) );
		curl_setopt( $ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));
		curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
		curl_setopt($ch, CURLOPT_URL, $this->url);
		$result = curl_exec($ch);
		curl_close($ch);
		return new CurlResponse($result, $this->url, $payload);
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

}
class CurlResponse 
{
	protected $content;
	protected $url;
	protected $request;
	function __construct ($content, $url, $request) {
		$response = json_decode($content);
		if (isset($response->status) && is_numeric($response->status) && $response->status >= 400) {
			throw new Exception('Error al enviar '. json_encode($request, JSON_PRETTY_PRINT). ' a la direccion '.$url);
		}
		$this->content = $content;
		$this->url = $url;
		$this->request = $request;
	}
	function decode() {
		return json_decode ($this->content);
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
		$this->content[$class][] = $response->decode();
	}

}
class AppCurlPostClient {
	protected $json;
	protected $baseurl;
	protected $output;
	static function start ($json, $baseurl=null) {
		if ($baseurl)
			return new self($json, $baseurl);
		else
			return new self($json);
	}
	function __construct ($json, $baseurl='http://host.docker.internal:8081/api/') {
		$this->json = $json;
		$this->baseurl = $baseurl;
		$this->output = AppCurlPostOutput::getInstance();
	}
	function renderUrl ($action) {
		return $this->baseurl.$action;
	}
	function execute () {
		foreach ($this->json as $name => $array) 
			foreach ($array as $payload)
				$this->output->add($name, CurlPost::init($this->renderUrl($name))->send($payload));
		return $this;
	}
	function printR () {
		print_r($this->output);
	}
	function printJson() {
		echo json_decoe($this->output, JSON_PRETTY_PRINT);
	}

}
AppCurlPostClient::start(JsonLoader::load($argv[1])->decode())->execute()->printR();
