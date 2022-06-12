package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Order;

import com.mongodb.DBRef;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    public Optional<Order> findByNumber(ObjectId aNumber);
    public Long countBySupplierId(ObjectId aIdSupplier);
    public List<Order> findAll();
    @Aggregation(pipeline = {
            "{ '$match' : { 'supplier' : ?0 } }",
            "{ '$addFields' : { 'cantidad' : { '$size' : '$items' } } }",
            "{ '$project' : { '_id' : 1 } }",
            "{ '$sort' : { 'cantidad' : -1 } }",
            "{ '$limit' : ?1 }"
    })
    List<ObjectId> findFirstsWithMaxItemsBySupplier(DBRef supplier, int size );
    Optional<Order> findTopByDateOfOrderBetweenOrderByTotalPriceDesc(Date start, Date end);
}