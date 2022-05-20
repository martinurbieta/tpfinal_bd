package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, ObjectId> {

    public Optional<Client> findByUsername(String aUsername);
}
