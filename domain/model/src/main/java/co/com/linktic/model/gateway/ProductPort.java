package co.com.linktic.model.gateway;

import co.com.linktic.model.ProductModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductPort {

    Mono<ProductModel> saveProduct(ProductModel product);

    Mono<ProductModel> getProductById(String id);

    Mono<List<ProductModel>> getAllProducts();

}
