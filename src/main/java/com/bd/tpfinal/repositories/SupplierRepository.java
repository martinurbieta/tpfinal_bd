package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, ObjectId> {
        public List<Supplier> findBySupplierTypeId(ObjectId aSupplierTypeId);
        public Optional<Supplier> findById(ObjectId anId);
        public List<Supplier> findAll();
        @Aggregation(pipeline = {
                "{'$lookup' : {'from' : 'order','localField' : '_id','foreignField' : 'supplier.$id','as' : 'orders'}}",
                "{'$addFields' : {'cantidad' : {'$size' : '$orders'}}}",
                "{'$sort' : {'cantidad' : -1}}",
                "{ '$limit' : 10 }"
        })
        List<Supplier> findBestDispatchersSupplierIds();

     //   @Query(value = "SELECT s.id FROM Order o JOIN o.supplier s JOIN o.qualification q WHERE q.score <= :score GROUP BY s.id")
        @Aggregation(pipeline = {
                "{'$lookup' : {'from' : 'order','localField' :'_id' ,'foreignField' : 'supplier.$id', 'as' : 'order'}}",
                "{'$unwind' : { path: '$order' } }",
                "{'$addFields' : { score: '$order.qualification.score' } }",
                "{'$match':{'score':{'$lte': ?0}}}",
                "{'$group' :{ _id : '$_id', 'cantidad' : { '$sum' : 1 }}}",
                "{'$project' :{ 'result' : [ '$_id', '$cantidad' ] }}"
        })

        List<ArrayList>  findByScoreLessThanEqual(@Param("score") Float score);
}
