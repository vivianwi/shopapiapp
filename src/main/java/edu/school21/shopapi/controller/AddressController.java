package edu.school21.shopapi.controller;

import edu.school21.openapi.api.AddressApi;
import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.GetClientsPaginationParameter;
import edu.school21.shopapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class AddressController implements AddressApi {

    private final AddressService addressService;


    @Override
    public UUID createAddress(AddressDto addressDto) {
        return addressService.save(addressDto).getId();
    }

    @Override
    public List<AddressDto> getAddresses(GetClientsPaginationParameter pagination) {
        return addressService.getAddresses(pagination.getOffset(), pagination.getLimit());
    }

}
