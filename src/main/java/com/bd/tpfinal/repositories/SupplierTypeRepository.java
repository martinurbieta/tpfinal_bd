package com.bd.tpfinal.repositories;


import com.bd.tpfinal.model.SupplierType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierTypeRepository extends MongoRepository<SupplierType, ObjectId> {
    public Optional<SupplierType> findById(ObjectId anId);
}
