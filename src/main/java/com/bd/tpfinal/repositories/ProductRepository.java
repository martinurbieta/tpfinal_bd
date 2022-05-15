package com.bd.tpfinal.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findBySupplierId(Long aSupplierId);
    public List<Product> findAll();
    public List<Product> findByProductTypeId(Long aProductTypeId);
    public Optional<Product> findProductById(Long anId);
    @Query(value = "SELECT pt.id, AVG(p.price) FROM Product p JOIN p.productType pt GROUP BY pt.id")
    List<ArrayList> findAllAveragePriceGroupByProductType();
}
