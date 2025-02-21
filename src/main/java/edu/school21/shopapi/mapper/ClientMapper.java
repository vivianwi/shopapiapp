package edu.school21.shopapi.mapper;


import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {CommonMapper.class, AddressMapper.class})
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
