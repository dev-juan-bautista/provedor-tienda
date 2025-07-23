package co.com.linktic.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    private static final String PRODUCT_PATH = "/api/v1/proveedor-tienda/producto";
    private static final String PRODUCT_ID_PATH = "/{id}";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
        return RouterFunctions.route()
                .path(PRODUCT_PATH, builder -> builder
                        .POST("",
                                accept(MediaType.APPLICATION_JSON)
                                        .and(contentType(MediaType.APPLICATION_JSON)),
                                productHandler::createProduct)
                        .GET(PRODUCT_ID_PATH,
                                accept(MediaType.APPLICATION_JSON)
                                        .and(contentType(MediaType.APPLICATION_JSON)),
                                productHandler::getProductById)
                        .GET("",
                                accept(MediaType.APPLICATION_JSON),
                                productHandler::getAllProducts)
                )
                .build();
    }
}
