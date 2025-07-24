package co.com.linktic.usecase;

import co.com.linktic.model.ProductModel;
import co.com.linktic.model.gateway.ProductPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductPort productPort;

    public Mono<ProductModel> saveProduct(ProductModel product) {
        return productPort.saveProduct(product);
    }

    public Mono<ProductModel> getProductById(String id) {
        return productPort.getProductById(id);
    }

    public Mono<List<ProductModel>> getAllProducts() {
        return productPort.getAllProducts();
    }

}
