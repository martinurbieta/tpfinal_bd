package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    public List<Product> findBySupplierId(ObjectId aSupplierId);
    public List<Product> findAll();
    public List<Product> findByProductTypeId(ObjectId aProductTypeId);
    public Optional<Product> findProductById(ObjectId anId);
//    @Query(value = "SELECT pt.id, AVG(p.price) FROM Product p JOIN p.productType pt GROUP BY pt.id")


    // en robo 3T funciona bien el siguiente comando

//        db.product.aggregate([
//            {$unwind: "$productType"},
//            {$group:{
//                _id:"$productType.$id",
//                        avgProductPrice:{$avg:"$price"}
//            }},
//            {$project:{
//                _id:0,
//                        productType:"$_id",
//                        avgProductPrice:1}
//            }
//            ])
//
//    @Aggregation("{$unwind: {path: $productType}}," +
//            "{$group:{_id:$productType.$id,avgProductPrice:{$avg:$price}}}," +
//            "{$project:{_id:0,productType:$_id,avgProductPrice:1}}}")

    @Aggregation(pipeline = {
            "{$group:{_id:$productType,avgProductPrice:{$avg:$price}}},"+
            "{$project:{_id:0,productType:_id,avgProductPrice:1}}"})

//    @Aggregation(pipeline = {
//            "{$group:{_id:$productType,avgProductPrice:{$avg:$price}}}"})



    List<ArrayList> findAllAveragePriceGroupByProductType();

// ejemplo.
//    @Aggregation(pipeline = { "{$group: { _id: '', total: {$avg: $price }}}" })
//    public double avg(double minQuantities);

    //    @Query("{name: ?1, username:?0}")
//    public User findUserX(String anUsername, String aName);
}
