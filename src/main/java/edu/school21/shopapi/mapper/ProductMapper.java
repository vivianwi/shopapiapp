package edu.school21.shopapi.mapper;

import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = CommonMapper.class)
public interface ProductMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    ProductDto toDto(Product entity);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "image", ignore = true)
    Product toEntity(ProductDto dto);
}

