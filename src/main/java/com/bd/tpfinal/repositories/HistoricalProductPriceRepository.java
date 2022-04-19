package com.bd.tpfinal.repositories;

import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.HistoricalProductPrice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalProductPriceRepository extends CrudRepository<HistoricalProductPrice, Long> {
    public Optional<HistoricalProductPrice> findFirstByProductIdOrderByStartDateDesc(Long aIdProduct);
    public void deleteByProductId (Long aIdProduct);
}
