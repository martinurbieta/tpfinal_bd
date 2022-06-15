package com.bd.tpfinal.repositories;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bd.tpfinal.model.HistoricalProductPrice;

import com.bd.tpfinal.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalProductPriceRepository extends CrudRepository<HistoricalProductPrice, Long> {
    public Optional<HistoricalProductPrice> findFirstByProductIdOrderByStartDateDesc(Long aIdProduct);
    public List<HistoricalProductPrice> findByProductId(Long anId);
    public void deleteByProductId (Long aIdProduct);
    public List<HistoricalProductPrice> findAllByStartDateBetweenAndProductId(Date startDate, Date finishDate, Long id);
    public List<HistoricalProductPrice> findAllByFinishDateBetweenAndProductId(Date startDate, Date finishDate, Long id);
}
