package edu.school21.shopapi.mapper;


import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = CommonMapper.class)
public interface ClientMapper {
    @Mapping(target = "addressId", source = "address.id")
    ClientDto toDto(Client client);

    @Mapping(target = "address.id", source = "addressId")
    Client toEntity(ClientDto clientDto);
}
