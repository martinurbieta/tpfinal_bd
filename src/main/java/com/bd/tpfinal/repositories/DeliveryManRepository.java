package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.DeliveryMan;
import com.bd.tpfinal.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryManRepository extends CrudRepository<DeliveryMan, Long> {

    public Optional<DeliveryMan> findByUsername(String aUsername);

    public List<DeliveryMan> findByFreeTrueAndActiveTrue();
    List<DeliveryMan> findTop10ByOrderByScoreDesc();

}
