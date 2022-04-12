package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.DeliveryMan;
import com.bd.tpfinal.model.Item;
import com.bd.tpfinal.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    public List<Item> findItemsByOrderID (Long id_order);
    public Optional<Item> findItemWithID(int idItem);


}
