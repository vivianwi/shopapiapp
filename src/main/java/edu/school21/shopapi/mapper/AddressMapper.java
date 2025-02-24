package edu.school21.shopapi.mapper;

import edu.school21.openapi.model.AddressDto;
import edu.school21.shopapi.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = CommonMapper.class)
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressDto address);

    AddressDto toDto(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(AddressDto addressDto, @MappingTarget Address address);
}
