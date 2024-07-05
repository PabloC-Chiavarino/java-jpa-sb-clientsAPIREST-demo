package com.pdev.clientsdemo;


import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class ClientService {

    private static final String notFound = "Client not found";
    private static final String found = "Client found:";

    @Autowired
    private ClientRepository clientRepository;


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        Optional<Client> clientCheck = clientRepository.findByDocPass(client.getDocPass());
        if (clientCheck.isPresent()) {
            System.out.println("Client already exists");

            return clientCheck.get();
        }
        Client savedClient = clientRepository.save(client);
        System.out.println("Successfully added client");

        return savedClient;
    }

    public Optional<Client> updateClient(Long id, Client data) {

        String dataName = data.getName();
        String dataLastName = data.getLastName();
        String dataEmail = data.getEmail();
        Long dataDocPass = data.getDocPass();

        Optional<Client> clientCheck = clientRepository.findById(id);
        if (clientCheck.isPresent()) {
            Client existingClient = clientCheck.get();

            String existingName = existingClient.getName();
            String existingLastName = existingClient.getLastName();
            String existingEmail = existingClient.getEmail();
            Long existingDocPass = existingClient.getDocPass();

                if  (!dataName.equals(existingName)) {
                    existingClient.setName(dataName);
                }
                if  (!dataLastName.equals(existingLastName)) {
                    existingClient.setLastName(dataLastName);
                }
                if  (!dataEmail.equals(existingEmail)) {
                    existingClient.setEmail(dataEmail);
                }
                if  (!dataDocPass.equals(existingDocPass)) {
                    existingClient.setDocPass(dataDocPass);
                }

            Client updatedClient = clientRepository.save(existingClient);
            System.out.println("Client updated");
            return Optional.of(updatedClient);
        } else {
        System.out.println(notFound);

        return Optional.empty();
        }
    }

    public Optional<Client> deleteClient(Long id) {
        Optional<Client> clientCheck = clientRepository.findById(id);
        if (clientCheck.isPresent()) {
            clientRepository.deleteById(id);
            System.out.println("Client deleted");
            return clientCheck;

        } else {
            System.out.println(notFound);

            return Optional.empty();
        }
    }

    public Optional<Client> findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            System.out.println(found);

            return client;
        } else {
            System.out.println(notFound);

            return Optional.empty();
        }
    }

    public Optional<Client> findByDocPass(Long docPass) {
        Optional<Client> client = clientRepository.findByDocPass(docPass);
        if (client.isPresent()) {
            System.out.println(found);
            return client;
        } else {
            System.out.println(notFound);

            return Optional.empty();
        }
    }

    public Optional<Client> findByLastName(String lastName) {
        Optional<Client> client = clientRepository.findByLastName(lastName);
        if (client.isPresent()) {
            System.out.println(found);

            return client;
        } else {
            System.out.println(notFound);

            return Optional.empty();
        }
    }

}
