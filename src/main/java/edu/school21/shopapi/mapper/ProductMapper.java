package edu.school21.shopapi.mapper;

import edu.school21.openapi.model.ProductDto;
import edu.school21.shopapi.model.Image;
import edu.school21.shopapi.model.Product;
import edu.school21.shopapi.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = CommonMapper.class)
public interface ProductMapper {

    @Mapping(target = "imageId", source = "image.id")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "createdAt", ignore = true)
    ProductDto toDto(Product entity);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "supplier", source = "supplier")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductDto dto, Supplier supplier, Image image);


    @Mapping(target = "image", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductDto dto, Supplier supplier);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(ProductDto dto, @MappingTarget Product entity);
}

