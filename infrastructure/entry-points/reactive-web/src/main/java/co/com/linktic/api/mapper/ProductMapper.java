package co.com.linktic.api.mapper;

import co.com.linktic.api.dto.request.ProductDto;
import co.com.linktic.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductModel toModel(ProductDto dto);

    ProductDto toDto(ProductModel model);
}
