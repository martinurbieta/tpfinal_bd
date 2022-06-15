package com.bd.tpfinal.repositories;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.HistoricalProductPrice;

import com.bd.tpfinal.model.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalProductPriceRepository extends MongoRepository<HistoricalProductPrice, ObjectId> {
    public Optional<HistoricalProductPrice> findFirstByProductIdOrderByStartDateDesc(ObjectId aIdProduct);
    public List<HistoricalProductPrice> findByProductId(ObjectId anId);
    public void deleteByProductId (ObjectId aIdProduct);
    public List<HistoricalProductPrice> findAllByStartDateBetweenAndProductId(Date startDate, Date finishDate, ObjectId id);
    public List<HistoricalProductPrice> findAllByFinishDateBetweenAndProductId(Date startDate, Date finishDate, ObjectId id);
}
