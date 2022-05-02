package com.bd.tpfinal.repositories;

import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.model.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findBySupplierId(Long aSupplierId);
    public Optional<Product> findProductById(Long anId);


}
