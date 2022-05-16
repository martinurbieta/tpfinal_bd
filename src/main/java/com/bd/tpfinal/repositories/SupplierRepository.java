package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Supplier;
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
        public List<Supplier> findBySupplierTypeId(Long aSupplierTypeId);
        public Optional<Supplier> findById(Long anId);
        public List<Supplier> findAll();
        @Query(value = "SELECT s.id FROM Order o JOIN o.supplier s GROUP BY s.id ORDER BY count(s) DESC")
        Page<Long> findBestDispatchersSupplierIds(Pageable pageable);
        @Query(value = "SELECT s.id_supplier FROM Order o JOIN o.item i JOIN i.product p JOIN p.supplier s WHERE o.score = :score GROUP BY s.id")
        List<Long> findByScoreLessThanEqual(@Param("score") Integer score);
}
