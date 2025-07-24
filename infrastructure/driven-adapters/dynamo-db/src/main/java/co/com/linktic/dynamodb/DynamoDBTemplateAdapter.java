package co.com.linktic.dynamodb;

import co.com.linktic.dynamodb.entity.ProductEntity;
import co.com.linktic.dynamodb.helper.TemplateAdapterOperations;
import co.com.linktic.dynamodb.mapper.ProductAdapterMapper;
import co.com.linktic.model.ProductModel;
import co.com.linktic.model.error.BusinessException;
import co.com.linktic.model.gateway.ProductPort;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DynamoDBTemplateAdapter extends TemplateAdapterOperations<ProductEntity, String, ProductEntity> implements ProductPort {

    private final DynamoDbAsyncTable<ProductEntity> productEntityTable;

    private final ProductAdapterMapper productMapper;

    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, ObjectMapper mapper, ProductAdapterMapper productMapper) {
        super(connectionFactory, mapper, d -> mapper.map(d, ProductEntity.class), "product");
        this.productEntityTable = connectionFactory.table("product",
                TableSchema.fromBean(ProductEntity.class));
        this.productMapper = productMapper;
    }


    @Override
    public Mono<ProductModel> saveProduct(ProductModel product) {
        String normalizedInputName = product.getName().toLowerCase();
        return Flux.from(productEntityTable.scan())
                .flatMapIterable(Page::items)
                .filter(existing -> existing.getName() != null &&
                                    existing.getName().toLowerCase().equals(normalizedInputName))
                .hasElements()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new BusinessException("Ya existe un producto con el mismo nombre", "LINKTIC_NAME_ALREADY_EXISTS"));
                    }
                    ProductEntity entity = ProductEntity.builder()
                            .id(UUID.randomUUID().toString())
                            .name(product.getName())
                            .price(product.getPrice())
                            .description(product.getDescription())
                            .build();
                    return save(entity).map(productMapper::toModel);
                });
    }

    @Override
    public Mono<ProductModel> getProductById(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .build();
        return Mono.fromFuture(productEntityTable.getItem(key))
                .switchIfEmpty(Mono.error(new BusinessException("El recurso solicitado no existe", "LINKTIC_DATA_NOT_FOUND")))
                .map(productMapper::toModel);
    }

    @Override
    public Mono<List<ProductModel>> getAllProducts() {
        return Flux.from(productEntityTable.scan().items())
                .map(productMapper::toModel)
                .collectList()
                .onErrorResume(e -> {
                    log.error("Error al obtener los productos", e);
                    return Mono.error(new RuntimeException("Error al obtener los productos", e));
                });
    }
}
