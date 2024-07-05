package com.pdev.clientsdemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByLastName(String lastName);
    Optional<Client> findByDocPass(Long docPass);
}
