package org.berns.clients.controllers;

import org.berns.clients.dto.ClientDTO;
import org.berns.clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public ResponseEntity<Page<ClientDTO>> findall(Pageable pageable){
        Page<ClientDTO> clients = clientService.findAll(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        ClientDTO client = clientService.save(clientDTO);
        return ResponseEntity.ok(client);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        clientDTO = clientService.update(id, clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
