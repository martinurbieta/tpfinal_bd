package com.bd.tpfinal.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.Product;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    public List<Product> findBySupplierId(ObjectId aSupplierId);
    public List<Product> findAll();
    public List<Product> findByProductTypeId(ObjectId aProductTypeId);
    public Optional<Product> findProductById(ObjectId anId);

    @Aggregation(pipeline = {
        "{$unwind:{ path: '$productType' }}",
        "{$group:{ _id : '$productType', 'avgProductPrice' : { '$avg' : '$price' }}}",
        "{$project:{ _id: 0, 'result' : [ '$_id', '$avgProductPrice' ] } }"
    })
    List<ArrayList> findAllAveragePriceGroupByProductType();
}
