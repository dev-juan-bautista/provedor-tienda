package co.com.linktic.api;

import co.com.linktic.api.util.ResponseBuilder;
import co.com.linktic.model.error.BusinessException;
import co.com.linktic.usecase.ProductUseCase;
import co.com.linktic.validator.dto.request.ProductDto;
import co.com.linktic.validator.engine.ValidatorEngine;
import co.com.linktic.validator.error.ValidationException;
import co.com.linktic.validator.mapper.ProductHelperMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductHandler {

    private final ProductUseCase productUseCase;

    private final ValidatorEngine validatorEngine;

    private final ProductHelperMapper productMapper;

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDto.class)
                .doOnNext(validatorEngine::validate)
                .map(productMapper::toModel)
                .flatMap(model ->
                        productUseCase.saveProduct(model)
                                .map(productMapper::toDto)
                                .flatMap(dto ->
                                        ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ResponseBuilder.buildSuccessResponse(dto, "El producto se ha creado con exito"))
                                )
                                .onErrorResume(ValidationException.class, ex ->
                                        ServerResponse.badRequest().bodyValue("Error de validación: " + ex.getMessage())
                                )
                )
                .onErrorResume(this::handleException);
    }

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return productUseCase.getProductById(id)
                .map(productMapper::toDto)
                .flatMap(product ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(ResponseBuilder.buildSuccessResponse(product, "Recurso solicitado con exito"))
                )
                .onErrorResume(this::handleException);
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        return productUseCase.getAllProducts()
                .flatMap(products ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(ResponseBuilder.buildSuccessResponse(products, "Se ha recuperado el listado de recursos con exito"))
                )
                .onErrorResume(this::handleException);
    }


    private Mono<ServerResponse> handleException(Throwable ex) {
        if (ex instanceof ValidationException validationException) {
            return ServerResponse.status(HttpStatus.BAD_REQUEST.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ResponseBuilder.buildValidationErrorResponse(validationException));
        } else if (ex instanceof BusinessException businessException) {
            return ServerResponse.status(HttpStatus.NOT_ACCEPTABLE.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ResponseBuilder.buildBusinessErrorResponse(businessException));
        }else {
            return ServerResponse.status(HttpStatus.NOT_ACCEPTABLE.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ResponseBuilder.buildGenericErrorResponse(ex));
        }
    }

}
