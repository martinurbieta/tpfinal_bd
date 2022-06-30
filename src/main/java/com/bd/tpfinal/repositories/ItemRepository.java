package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {
    public void deleteByProductId (ObjectId aIdProduct);
}
