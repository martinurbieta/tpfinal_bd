package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.model.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
    public List<ProductType> findByIdIn(List<Long> ids);

}
