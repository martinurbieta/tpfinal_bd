package com.bd.tpfinal.repositories;


import com.bd.tpfinal.model.SupplierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTypeRepository extends CrudRepository<SupplierType, Long> {


}
