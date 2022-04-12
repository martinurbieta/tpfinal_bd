package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Item;
import com.bd.tpfinal.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public Optional<Order> findOrderByNumber(Long aNumber);

}
