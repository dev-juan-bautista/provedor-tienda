package co.com.linktic.api;

import co.com.linktic.validator.dto.request.ProductDto;
import co.com.linktic.validator.dto.response.ApiGenericResponse;
import co.com.linktic.validator.swagger.DocProductService;
import co.com.linktic.validator.swagger.ResponseHttpStatus;
import co.com.linktic.validator.swagger.wrapper.ResponseProductWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class ProductRouter {

    private static final String PRODUCT_PATH = "/api/v1/proveedor-tienda/producto";
    private static final String PRODUCT_ID_PATH = "/{id}";

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = PRODUCT_PATH,
                    method = RequestMethod.POST,
                    beanClass = ProductHandler.class,
                    beanMethod = DocProductService.BEAN_OPERATION_SERVICE_CREATE,
                    operation = @Operation(
                            operationId = DocProductService.BEAN_OPERATION_SERVICE_CREATE,
                            summary = DocProductService.SUMMARY_SERVICE_CREATE,
                            description = DocProductService.DESCRIPTION_SERVICE_CREATE,
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ProductDto.class),
                                            examples = @ExampleObject(
                                                    name = DocProductService.REQUEST_NAME_SERVICE_CREATE,
                                                    value = DocProductService.REQUEST_VALUE_SERVICE_CREATE
                                            )
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_ACCEPTED_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_ACCEPTED_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ResponseProductWrapper.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_SUCCESS_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_ACCEPTED_CREATE
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_BAD_REQUEST_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_BAD_REQUEST_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ApiGenericResponse.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_BAD_REQUEST_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_BAD_REQUEST_CREATE
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_NOT_ACCEPTABLE_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_NOT_ACCEPTABLE_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ApiGenericResponse.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_NOT_ACCEPTABLE_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_NOT_ACCEPTABLE_CREATE
                                                    )
                                            )
                                    ),
                            }
                    )
            ),
            @RouterOperation(
                    path = PRODUCT_PATH + PRODUCT_ID_PATH,
                    method = RequestMethod.GET,
                    beanClass = ProductHandler.class,
                    beanMethod = DocProductService.BEAN_OPERATION_SERVICE_FIND_BY_ID,
                    operation = @Operation(
                            operationId = DocProductService.BEAN_OPERATION_SERVICE_FIND_BY_ID,
                            summary = DocProductService.SUMMARY_SERVICE_FIND_BY_ID,
                            description = DocProductService.DESCRIPTION_SERVICE_FIND_BY_ID,
                            parameters = {
                                    @Parameter(
                                            name = DocProductService.PARAMETER_PATH_ID_GENERIC,
                                            description = DocProductService.PARAMETER_PATH_DESCRIPTION_GENERIC,
                                            required = true,
                                            in = ParameterIn.PATH,
                                            schema = @Schema(
                                                    type = DocProductService.PARAMETER_PATH_SCHEMA_TYPE_GENERIC,
                                                    format = DocProductService.PARAMETER_PATH_SCHEMA_FORMAT_GENERIC
                                            ),
                                            example = DocProductService.PARAMETER_PATH_EXAMPLE_GENERIC
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_OK_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_OK_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ApiGenericResponse.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_SUCCESS_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_OK_FIND_PRODUCT
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_NOT_FOUND_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_NOT_FOUND_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ApiGenericResponse.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_BAD_REQUEST_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_BAD_REQUEST_FIND_PRODUCT
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = PRODUCT_PATH,
                    method = RequestMethod.GET,
                    beanClass = ProductHandler.class,
                    beanMethod = DocProductService.BEAN_OPERATION_SERVICE_FIND_ALL,
                    operation = @Operation(
                            operationId = DocProductService.BEAN_OPERATION_SERVICE_FIND_ALL,
                            summary = DocProductService.SUMMARY_SERVICE_FIND_ALL,
                            description = DocProductService.DESCRIPTION_SERVICE_FIND_ALL,
                            responses = {
                                    @ApiResponse(
                                            responseCode = ResponseHttpStatus.HTTP_STATUS_OK_CODE,
                                            description = ResponseHttpStatus.HTTP_STATUS_OK_MESSAGE,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = ApiGenericResponse.class),
                                                    examples = @ExampleObject(
                                                            name = DocProductService.RESPONSE_NAME_SERVICE_SUCCESS_GENERIC,
                                                            value = DocProductService.RESPONSE_VALUE_SERVICE_OK_FIND_ALL
                                                    )
                                            )
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
        return RouterFunctions.route()
                .path(PRODUCT_PATH, builder -> builder
                        .POST("",
                                accept(MediaType.APPLICATION_JSON)
                                        .and(contentType(MediaType.APPLICATION_JSON)),
                                productHandler::createProduct)
                        .GET(PRODUCT_ID_PATH,
                                accept(MediaType.APPLICATION_JSON),
                                productHandler::getProductById)
                        .GET("",
                                accept(MediaType.APPLICATION_JSON),
                                productHandler::getAllProducts)
                )
                .build();
    }
}
