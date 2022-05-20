package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.model.ProductType;
import com.bd.tpfinal.model.SupplierType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends MongoRepository<ProductType, ObjectId> {
    public List<ProductType> findByIdIn(List<ObjectId> ids);
    public List<ProductType> findAll();
    public Optional<ProductType> findProductTypeById(ObjectId anId);

}
