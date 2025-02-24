package edu.school21.shopapi.controller;


import edu.school21.openapi.api.ClientApi;
import edu.school21.openapi.model.AddressDto;
import edu.school21.openapi.model.ClientDto;
import edu.school21.openapi.model.GetClientsPaginationParameter;
import edu.school21.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Override
    public void createClient(ClientDto clientDto) {
        clientService.save(clientDto);
    }

    @Override
    public void deleteClient(UUID clientId) {
        clientService.delete(clientId);
    }

    @Override
    public List<ClientDto> getClients(GetClientsPaginationParameter pagination) {
        return clientService.getClients(pagination.getLimit(), pagination.getOffset());
    }
    @Override
    public List<ClientDto> searchClients(String clientName, String clientSurname) {
        return clientService.getClients(clientName, clientSurname);
    }

    @Override
    public void updateClientAddress(UUID clientId, AddressDto addressDto) {
        clientService.updateClientAddress(clientId, addressDto);
    }
}

