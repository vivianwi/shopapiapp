package edu.school21.shopapi.service;


import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.error.ServiceException;
import edu.school21.shopapi.mapper.ClientMapper;
import edu.school21.shopapi.model.Address;
import edu.school21.shopapi.model.Client;
import edu.school21.shopapi.repository.ClientRepository;
import edu.school21.shopapi.specification.ClientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    public final ClientRepository clientRepository;
    public final ClientMapper clientMapper;
    public final AddressService addressService;

    public List<ClientDto> getClients(String clientName, String clientSurname) {
        Specification<Client> spec = ClientSpecification.withFilters(clientName, clientSurname);

        return clientRepository.findAll(spec)
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<ClientDto> getClients(Integer limit, Integer offset) {
        Pageable pageable;

        if (limit != null && offset != null) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = Pageable.unpaged();
        }
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.getContent()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(ClientDto clientDto) {
        Address savedAddress = addressService.save(clientDto.getAddress());
        Client client = clientMapper.toEntity(clientDto);
        client.setAddress(savedAddress);
        clientRepository.save(client);
    }

    public void delete(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ServiceException("Клиент с id" + clientId + " не найден");
        }
        clientRepository.deleteById(clientId);
    }

    public void updateClientAddress(UUID clientId, AddressDto addressDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ServiceException("No such client"));
        addressService.updateAddress(client.getAddress(), addressDto);
    }
}
