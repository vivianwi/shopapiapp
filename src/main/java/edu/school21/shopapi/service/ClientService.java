package edu.school21.shopapi.service;


import edu.school21.openapi.model.ClientDto;
import edu.school21.shopapi.mapper.ClientMapper;
import edu.school21.shopapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    public final ClientRepository clientRepository;
    public final ClientMapper clientMapper;

    public List<ClientDto> getClients(){
        return List.of();
    }

    public void save(ClientDto clientDto){
        clientRepository.save(clientMapper.toEntity(clientDto));
    }

}
