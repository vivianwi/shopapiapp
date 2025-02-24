package edu.school21.shopapi.mapper;

import edu.school21.openapi.model.ImageDto;
import edu.school21.shopapi.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {CommonMapper.class, AddressMapper.class})
public interface ImageMapper {
    @Mapping(target = "image", ignore = true)
    ImageDto toDto(Image image);
}
