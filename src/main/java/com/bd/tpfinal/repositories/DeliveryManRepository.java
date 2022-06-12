package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.DeliveryMan;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryManRepository extends MongoRepository<DeliveryMan, ObjectId> {

    public Optional<DeliveryMan> findByUsername(String aUsername);

    @Aggregation(pipeline = {
            "{'$match' : { 'free' : true, 'active' : true }}",
            "{ '$sample' : { 'size' : 1 } }"
    })
    public Optional<DeliveryMan> findRandomlyByFreeTrueAndActiveTrue();
    Page<DeliveryMan> findByOrderByScoreDesc(Pageable pageable);

}
