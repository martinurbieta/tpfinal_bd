package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Supplier;
import com.bd.tpfinal.model.SupplierType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
//        public List<Supplier> findSupplierByProductTypeId(Long aProductTypeId);
        public Optional<Supplier> findById(Long anId);
        @Query(value = "SELECT s.id FROM Order o JOIN o.supplier s GROUP BY s.id ORDER BY count(s) DESC")
        Page<Long> findBestDispatchersSupplierIds(Pageable pageable);
}
