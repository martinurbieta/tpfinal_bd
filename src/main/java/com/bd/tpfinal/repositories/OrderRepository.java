package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Order;

import com.bd.tpfinal.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public Optional<Order> findByNumber(Long aNumber);
    public Long countBySupplierId(Long aIdSupplier);
    public List<Order> findAll();
    @Query(value = "SELECT o.number FROM Order o JOIN o.items i JOIN i.product p WHERE p.supplier.id = :supplier GROUP BY o.number ORDER BY count(o) DESC")
    Page<Long> findFirstsWithMaxItemsBySupplier(@Param("supplier") Long supplier, Pageable pageable);
    Optional<Order> findTopByDateOfOrderBetweenOrderByTotalPriceDesc(Date start, Date end);
}