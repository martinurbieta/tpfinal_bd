package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.ProductType;
import com.bd.tpfinal.model.SupplierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
    public Optional<ProductType> findProductTypeById(Long anId);

}
