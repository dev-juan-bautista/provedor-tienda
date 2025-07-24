package co.com.linktic.validator.mapper;

import co.com.linktic.model.ProductModel;
import co.com.linktic.validator.dto.request.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductHelperMapper {

    ProductModel toModel(ProductDto dto);

    ProductDto toDto(ProductModel model);
}
