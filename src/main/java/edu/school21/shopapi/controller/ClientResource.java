package edu.school21.shopapi.controller;


import edu.school21.openapi.api.ClientApi;
import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientResource implements ClientApi {

    private final ClientService clientService;

    @Override
    public void createClient(ClientDto clientDto) {
        clientService.save(clientDto);
    }

    @Override
    public void deleteClient(UUID clientId) {

    }

    @Override
    public List<ClientDto> getClients(String clientName, String clientSurname) {
        return List.of();
    }

    @Override
    public void updateClientAddress(UUID clientId, AddressDto addressDto) {

    }
}

