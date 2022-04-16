package com.bd.tpfinal.model;

import org.hibernate.boot.model.source.spi.FetchCharacteristics;

import java.util.Date;
import java.util.List;
import  javax.persistence.*;

@Entity
@Table(name="historicalproductprice")
public class HistoricalProductPrice {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private float price;

    private Date startDate;

    private Date finishDate;

    @OneToMany(mappedBy = "historicalproductprice", fetch = FetchType.LAZY)
    private List<Product> products;

    public HistoricalProductPrice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}

