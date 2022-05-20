package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Address;
import com.bd.tpfinal.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address, ObjectId> {
    public Optional<Address> findById(ObjectId anId);
}
