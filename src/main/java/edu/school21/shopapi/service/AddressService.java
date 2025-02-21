package edu.school21.shopapi.service;


import edu.school21.openapi.model.AddressDto;
import edu.school21.shopapi.mapper.AddressMapper;
import edu.school21.shopapi.model.Address;
import edu.school21.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public Address save(AddressDto address) {
        return addressRepository.save(addressMapper.toEntity(address));
    }
    public AddressDto mapToDto(Address address) {
        return addressMapper.toDto(address);
    }
}
