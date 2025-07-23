package co.com.linktic.api;

import co.com.linktic.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductHandler {

    //private final ProductUseCase productUseCase;

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("OK");
    }

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("OK");
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("OK");
    }
}
