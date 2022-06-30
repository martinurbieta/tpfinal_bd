Uso del curlpost.php:

php curlpost.php -i <archivo-de-entrada> [-o <archivo-de-salida>] [-s] [-k <indices-objetos-procesados-grabados-en-db>] [-u <url-base-para-conectar-a-la-aplicacion>]

-s: graba en un archivo keys.<archivo-de-salida>.json los indices de las claves grabadas.

El inicio del archivo indica que tipo de método http va utilizar para hacer la conexión, el archivo de entrada tiene que empezar con: post, delete, put o get

Procesamiento de los archivos:

Inicializacion de la base de datos con datos de prueba

php curlpost.php -i put.endpoints.json -o modificados.json  -k keys.creados.json

Prueba de los end points que son por el método put (1-4):

php curlpost.php -i put.endpoints.json -o modificados.json -k keys.creados.json

Prueba de los end points que son por el método delete (5):

php curlpost.php -i delete.endpoints.json -o borrados.json  -k keys.creados.json

Prueba de los end points que son por el método put (6-15):
php curlpost.php -i get.endpoints.json -o consultas.json -k keys.creados.json

-----

El archivo de salida esta compuesto por:

Url: que es la url traducida del indice que indica cual es la consulta
Request: Es el json que se envia al servidor para el procesamiento
Response: Es el Json de respuesta de la aplicacion

-----

El archivo de entrada se compone de un json que tiene como indice la parte de la url que completa la url base para hacer la consulta.
Este índice puede utilizar los índices pasados por parámetros si tiene que inyectar una clave de un objeto grabado de la forma <nombre-entidad>:<indice-guardado>
Por ejemplo: supplier:5, lo reemplazará por el indice que diga la posición 5 del array guardado con el índice supplier.

Los hijos de este indice son los request que se van a mandar al end-point para que lo procese