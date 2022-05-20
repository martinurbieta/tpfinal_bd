package com.bd.tpfinal.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    public List<Product> findBySupplierId(ObjectId aSupplierId);
    public List<Product> findAll();
    public List<Product> findByProductTypeId(ObjectId aProductTypeId);
    public Optional<Product> findProductById(ObjectId anId);
//    @Query(value = "SELECT pt.id, AVG(p.price) FROM Product p JOIN p.productType pt GROUP BY pt.id")
    List<ArrayList> findAllAveragePriceGroupByProductType();
}
