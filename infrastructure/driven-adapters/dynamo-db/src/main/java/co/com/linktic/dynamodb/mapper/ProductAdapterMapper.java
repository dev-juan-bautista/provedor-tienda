package co.com.linktic.dynamodb.mapper;

import co.com.linktic.dynamodb.entity.ProductEntity;
import co.com.linktic.model.ProductModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true)
)
public interface ProductAdapterMapper {

    ProductModel toModel(ProductEntity entity);
    ProductEntity toEntity(ProductModel model);

}
