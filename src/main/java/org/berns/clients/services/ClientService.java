package org.berns.clients.services;

import jakarta.persistence.EntityNotFoundException;
import org.berns.clients.dto.ClientDTO;
import org.berns.clients.entities.Client;
import org.berns.clients.repository.ClientRepository;
import org.berns.clients.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> new ClientDTO(client));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO){
        Client client = new Client();
        client = copyDtoToEntity(client, clientDTO);
        clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) throws EntityNotFoundException {
        try {
            Client client = clientRepository.getReferenceById(id);
            client = copyDtoToEntity(client, clientDTO);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        clientRepository.deleteById(id);
    }


    public Client copyDtoToEntity(Client client, ClientDTO clientDTO) {
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());

        return client;
    }
}
