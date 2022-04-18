package com.bd.tpfinal.repositories;


import com.bd.tpfinal.model.SupplierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierTypeRepository extends CrudRepository<SupplierType, Long> {
    public Optional<SupplierType> findSupplierTypeById(long anId);
}
