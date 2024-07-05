package com.pdev.clientsdemo;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController @RequestMapping (path = "api/v1/clients") @AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        try {
            List<Client> clients = clientService.findAll();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Client> create(@RequestBody @Valid Client data) {
        try {
            Client client = clientService.saveClient(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
        public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody @Valid Client data) {
        try {
            Optional<Client> client = clientService.updateClient(id, data);
            if (client.isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(client.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Client> delete(@PathVariable @RequestBody @Valid Long id) {
        try {
            Optional<Client> client = clientService.deleteClient(id);
            if(client.isPresent()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> findOne(@PathVariable @NonNull Long id) {
        try {
            Optional<Client> client = clientService.findById(id);
            if (client.isPresent()) {
                return ResponseEntity.ok(client.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/last_name/{lastName}")
    public ResponseEntity<Client> findByLastName( @PathVariable @NotBlank String lastName) {
        try {
            Optional<Client> client = clientService.findByLastName(lastName);
            if (client.isPresent()) {
                return ResponseEntity.ok(client.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/doc_pass/{docPass}")
    public ResponseEntity<Client> findByDocPass( @PathVariable @NotNull Long docPass) {
        try {
            Optional<Client> client = clientService.findByDocPass(docPass);
            if (client.isPresent()) {
                return ResponseEntity.ok(client.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
