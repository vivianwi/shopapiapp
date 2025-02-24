package edu.school21.shopapi.mapper;

import edu.school21.openapi.model.SupplierDto;
import edu.school21.shopapi.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = CommonMapper.class)
public interface SupplierMapper {
    SupplierDto toDto(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    Supplier toEntity(SupplierDto dto);
}
