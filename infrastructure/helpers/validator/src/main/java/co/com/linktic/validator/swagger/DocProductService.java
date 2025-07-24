package co.com.linktic.validator.swagger;

public class DocProductService {

    public DocProductService() {
    }

    public static final String RESPONSE_NAME_SERVICE_SUCCESS_GENERIC = "Ejemplo de respuesta exitosa";
    public static final String RESPONSE_NAME_SERVICE_BAD_REQUEST_GENERIC = "Ejemplo de respuesta con errores";
    public static final String RESPONSE_NAME_SERVICE_NOT_FOUND_GENERIC = "Ejemplo de respuesta informacion no encontrada";
    public static final String RESPONSE_NAME_SERVICE_NOT_ACCEPTABLE_GENERIC = "Ejemplo de respuesta informacion rechazada";


    public static final String BEAN_OPERATION_SERVICE_CREATE = "createProduct";
    public static final String SUMMARY_SERVICE_CREATE = "Crea un producto";
    public static final String DESCRIPTION_SERVICE_CREATE = "Servicio encargado de registrar en la dynamo un producto";
    public static final String REQUEST_NAME_SERVICE_CREATE = "Request para crear un producto";
    public static final String REQUEST_VALUE_SERVICE_CREATE = """
            {
                "nombre": "Agua",
                "precio": 100,
                "descripccion": "Brisa 350ml"
            }
            """;
    public static final String RESPONSE_VALUE_SERVICE_ACCEPTED_CREATE = """
            {
                "resultado": true,
                "datos": {
                    "id": "119f8177-f4ea-4a7e-ad8a-314810dde3ad",
                    "nombre": "Maracuya",
                    "precio": 100.0,
                    "descripccion": "Agro campo x 500gr"
                },
                "codigoRespuesta": "LINKTIC_SERVICE_SUCCESS",
                "mensaje": "El producto se ha creado con exito",
                "fechaHora": "2025-07-23T17:06:23.210974"
            }
            """;
    public static final String RESPONSE_VALUE_SERVICE_BAD_REQUEST_CREATE = """
            {
                "resultado": false,
                "errores": [
                    {
                        "mensaje": "El campo precio es obligatorio",
                        "campo": "precio"
                    },
                    {
                        "mensaje": "El campo nombre es obligatorio",
                        "campo": "nombre"
                    }
                ],
                "codigoRespuesta": "LINKTIC_SERVICE_VALIDATION_ERROR",
                "mensaje": "Errores de validación en los campos",
                "fechaHora": "2025-07-23T17:13:59.694252"
            }
            """;
    public static final String RESPONSE_VALUE_SERVICE_NOT_ACCEPTABLE_CREATE = """
            {
                "resultado": false,
                "codigoRespuesta": "LINKTIC_NAME_ALREADY_EXISTS",
                "mensaje": "Ya existe un producto con el mismo nombre",
                "fechaHora": "2025-07-23T17:15:33.384517"
            }
            """;

    public static final String BEAN_OPERATION_SERVICE_FIND_BY_ID = "getProductById";
    public static final String SUMMARY_SERVICE_FIND_BY_ID = "Consulta un producto por su id";
    public static final String DESCRIPTION_SERVICE_FIND_BY_ID = "Servicio encargado de consultar un registro en la dynamo por su id";
    public static final String PARAMETER_PATH_ID_GENERIC = "id";
    public static final String PARAMETER_PATH_DESCRIPTION_GENERIC = "id del registro producto en la dynamodb5";
    public static final String PARAMETER_PATH_SCHEMA_TYPE_GENERIC = "string";
    public static final String PARAMETER_PATH_SCHEMA_FORMAT_GENERIC = "uuid";
    public static final String PARAMETER_PATH_EXAMPLE_GENERIC = "b3c14689-06cc-4a15-9ef7-215963cb3d25";
    public static final String RESPONSE_VALUE_SERVICE_OK_FIND_PRODUCT = """
            {
                "resultado": true,
                "datos": {
                    "id": "20e846ad-20bc-4962-9e24-8771fa8b6b8b",
                    "nombre": "Salchichon",
                    "precio": 5000.0,
                    "descripccion": "Marca Zenu x 100 gr"
                },
                "codigoRespuesta": "LINKTIC_SERVICE_SUCCESS",
                "mensaje": "Recurso solicitado con exito",
                "fechaHora": "2025-07-23T16:53:39.041599"
            }
            """;
    public static final String RESPONSE_VALUE_SERVICE_BAD_REQUEST_FIND_PRODUCT = """
            {
                "resultado": false,
                "codigoRespuesta": "LINKTIC_DATA_NOT_FOUND",
                "mensaje": "El recurso solicitado no existe",
                "fechaHora": "2025-07-23T19:38:53.088719"
            }
            """;
    public static final String BEAN_OPERATION_SERVICE_FIND_ALL = "getAllProducts";
    public static final String SUMMARY_SERVICE_FIND_ALL = "Consulta la lista de productos";
    public static final String DESCRIPTION_SERVICE_FIND_ALL = "Servicio encargado de consultar todos los registros de la dynamo";
    public static final String RESPONSE_VALUE_SERVICE_OK_FIND_ALL = """
            {
                "resultado": true,
                "datos": [
                    {
                        "id": "20e846ad-20bc-4962-9e24-8771fa8b6b8b",
                        "name": "Salchichon",
                        "price": 5000.0,
                        "description": "Marca Zenu x 100 gr"
                    },
                    {
                        "id": "8195b751-cf9f-44a6-a3bc-b543755b9bb1",
                        "name": "Agua",
                        "price": 100.0,
                        "description": "Brisa 350ml"
                    }
                ],
                "codigoRespuesta": "LINKTIC_SERVICE_SUCCESS",
                "mensaje": "Se han recuperado el listado de recursos con exito",
                "fechaHora": "2025-07-23T16:57:40.507256"
            }
            """;
}
