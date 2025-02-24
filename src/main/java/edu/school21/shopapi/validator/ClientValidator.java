package edu.school21.shopapi.validator;

import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {
    private final AddressRepository addressRepository;

    public void validateClient(ClientDto clientDto) {
        checkAddressExistence(clientDto);
    }

    public void checkAddressExistence(ClientDto product) {
        if (addressRepository.findById(product.getAddress().getId()).isEmpty()){
            throw new ServiceException("No address found");
        }
    }

}
