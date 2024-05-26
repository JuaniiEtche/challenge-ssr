# Challenge-SSR

## Instrucciones para Compilar y Ejecutar la Aplicación

Para compilar y ejecutar la aplicación Challenge-SSR, sigue los siguientes pasos detallados:

### Requisitos Previos

Asegúrate de tener instaladas y configuradas las siguientes herramientas:

- **Java Development Kit (JDK)**: Recomendado JDK 11 o superior.
- **Apache Maven**: Herramienta de gestión de proyectos y dependencias.

### Compilación del Proyecto
1. Navega al directorio raíz del proyecto donde se encuentra el archivo pom.xml.
2. Ejecuta el siguiente comando para limpiar y compilar el proyecto, generando el archivo JAR en la carpeta target:
   mvn clean package


### Ejecución de la Aplicación
1. Dirígete a la carpeta target donde se ha generado el archivo JAR. Por ejemplo:
   cd target
2. Ejecuta la aplicación utilizando el siguiente comando:
   java -jar challenge-ssr-1.0.0.jar

### Estructura del proyecto

El proyecto Challenge-SSR está estructurado de la siguiente manera:
- api: Contiene la lógica de negocio de la aplicación, tales como los controladores y servicios.
- config: Contiene la configuración de la aplicación, tales como la configuración de swagger.
- utils: Contiene clases de utilidad para la aplicación, tales como los filtros de los logs.

Dentro de la carpeta api, se encuentran los siguientes paquetes:
- controllers: Contiene los controladores de la aplicación.
- dtos: Contiene los objetos de transferencia de datos (DTO) de la aplicación.
- models: Contiene las entidades y repositorios de la aplicación.
- services: Contiene los servicios con la lógica del negocio de la aplicación.
- exceptions: Contiene las excepciones personalizadas de la aplicación.
- mappers: Contiene los mappers para convertir entre entidades y DTOs.


### Endpoints de la aplicación:
- POST /database/localidad: Crea una localidad en la base de datos.
Entrada:
```json
{
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
Salida: Mensaje de confirmación.

- DELETE /database/localidad: Elimina una localidad de la base de datos.
Entrada: Por parámetro se recibe el id de la provincia a eliminar.
Salida: Mensaje de confirmación.

- GET /database/localidad/get-all: Obtiene todas las localidades de la base de datos.
Salida:
```json
[
   {
      "id": 0,
      "nombre": "string",
      "codigoPostal": "string",
      "provinciaId": 0
   }
]
```
- GET /database/localidad/get-by-id: Obtiene una localidad por su id de la base de datos.
Entrada: Por parámetro se recibe el id de la localidad a obtener.
Salida:
```json
{
   "id": 0,
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
- PUT /database/localidad/update: Actualiza una localidad en la base de datos.
Entrada: Se recibe por parámetro el id de la localidad a actualizar y el objeto con los nuevos datos.
```json
{
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
- POST /database/provincia: Crea una provincia en la base de datos.
Entrada:
```json
{
   "nombre": "string",
   "codigo31662": "string"
}
```
- DELETE /database/provincia: Elimina una provincia de la base de datos.
Entrada: Por parámetro se recibe el id de la provincia a eliminar.
Salida: Mensaje de confirmación.

- GET /database/provincia/get-all: Obtiene todas las provincias de la base de datos.
Salida:
```json
[
   {
      "id": 0,
      "nombre": "string",
      "codigo31662": "string"
   }
]
```
- GET /database/provincia/get-by-id: Obtiene una provincia por su id de la base de datos.
Entrada: Por parámetro se recibe el id de la provincia a obtener.
Salida:
```json
{
   "id": 0,
   "nombre": "string",
   "codigo31662": "string"
}
```
- PUT /database/provincia/update: Actualiza una provincia en la base de datos.
Entrada: Se recibe por parámetro el id de la provincia a actualizar y el objeto con los nuevos datos.
```json
{
   "nombre": "string",
   "codigo31662": "string"
}
```
Salida: Mensaje de confirmación.
- POST /csv/localidad: Crea una localidad en el archivo CSV.
Entrada:
```json
{
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
Salida: Mensaje de confirmación.
- DELETE /csv/localidad: Elimina una localidad del archivo CSV.
Entrada: Por parámetro se recibe el id de la localidad a eliminar.
Salida: Mensaje de confirmación.
- GET /csv/localidad/get-all: Obtiene todas las localidades del archivo CSV.
Salida:
```json
[
   {
      "id": 0,
      "nombre": "string",
      "codigoPostal": "string",
      "provinciaId": 0
   }
]
```
- GET /csv/localidad/get-by-id: Obtiene una localidad por su id del archivo CSV.
Entrada: Por parámetro se recibe el id de la localidad a obtener.
Salida:
```json
{
   "id": 0,
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
- PUT /csv/localidad/update: Actualiza una localidad en el archivo CSV.
Entrada: Se recibe por parámetro el id de la localidad a actualizar y el objeto con los nuevos datos.
```json
{
   "nombre": "string",
   "codigoPostal": "string",
   "provinciaId": 0
}
```
Salida: Mensaje de confirmación.
- POST /csv/provincia: Crea una provincia en el archivo CSV.
Entrada:
```json
{
   "nombre": "string",
   "codigo31662": "string"
}
```
Salida: Mensaje de confirmación.
- DELETE /csv/provincia: Elimina una provincia del archivo CSV.
Entrada: Por parámetro se recibe el id de la provincia a eliminar.
Salida: Mensaje de confirmación.
- GET /csv/provincia/get-all: Obtiene todas las provincias del archivo CSV.
Salida:
```json
[
   {
      "id": 0,
      "nombre": "string",
      "codigo31662": "string"
   }
]
```
- GET /csv/provincia/get-by-id: Obtiene una provincia por su id del archivo CSV.
Entrada: Por parámetro se recibe el id de la provincia a obtener.
Salida:
```json
{
   "id": 0,
   "nombre": "string",
   "codigo31662": "string"
}
```
- PUT /csv/provincia/update: Actualiza una provincia en el archivo CSV.
Entrada: Se recibe por parámetro el id de la provincia a actualizar y el objeto con los nuevos datos.
```json
{
   "nombre": "string",
   "codigo31662": "string"
}
```
Salida: Mensaje de confirmación.
- POST /export-csv/export: Exporta las localidades y provincias a un archivo CSV.
Salida: Mensaje de confirmación.
- GET /hybrid/localidad: Obtiene todas las localidades de la base de datos y del archivo CSV.
Entrada: Por parámetro se recibe el id de la localidad a obtener.
Salida:

```json
{
   "database": {
      "localidad":{
            "id": 0,
            "nombre": "string",
            "codigoPostal": "string",
            "provinciaId": 0
      },
      "timeElapsed": 0
   },
    "csv": {
      "localidad": {
        "id": 0,
        "nombre": "string",
        "codigoPostal": "string",
        "provinciaId": 0
      },
      "timeElapsed": 0
    }
}
```

- GET /hybrid/provincia: Obtiene todas las provincias de la base de datos y del archivo CSV.
Entrada: Por parámetro se recibe el id de la provincia a obtener.
Salida:
```json
{
   "database": {
      "provincia":{
            "id": 0,
            "nombre": "string",
            "codigo31662": "string"
      },
      "timeElapsed": 0
   },
    "csv": {
      "provincia": {
        "id": 0,
        "nombre": "string",
        "codigo31662": "string"
      },
      "timeElapsed": 0
    }
}
```
## Adicional:
- Se ha implementado un analisis con trivy para la detección de vulnerabilidades en las dependencias del proyecto. Las cuales ya fueron corregidas con éxito.
