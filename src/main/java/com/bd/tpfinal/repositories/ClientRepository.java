package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    public Optional<Client> findByUsername(String aUsername);
}
