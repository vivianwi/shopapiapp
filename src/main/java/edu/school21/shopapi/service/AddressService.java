package edu.school21.shopapi.service;


import edu.school21.openapi.model.AddressDto;
import edu.school21.shopapi.mapper.AddressMapper;
import edu.school21.shopapi.model.Address;
import edu.school21.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    //TODO: стоит подумать менять ли способ реализации
    public Address save(AddressDto address) {
        return addressRepository.save(addressMapper.toEntity(address));
    }

    public void updateAddress(Address addressEntity, AddressDto address) {
        addressMapper.updateEntityFromDto(address, addressEntity);
        addressRepository.save(addressEntity);
    }

    public List<AddressDto> getAddresses(Integer offset, Integer limit) {
        Pageable pageable;

        if (limit != null && offset != null) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Address> addressPage = addressRepository.findAll(pageable);
        return addressPage.getContent()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }
}
