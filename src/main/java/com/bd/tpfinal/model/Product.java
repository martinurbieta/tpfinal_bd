package com.bd.tpfinal.model;

import javax.persistence.*;


import java.util.List;

@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name = "id_product", nullable = false)
    private Long id_product;

    private String name;

    private float price;

    private float weight;

    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {})
    private List<Item> items;

    @ManyToMany
    private List<Supplier> suppliers;

    @ManyToMany
    private List<ProductType> types;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "historicalproductprice_id")
    private HistoricalProductPrice historicalproductprice;



    public Product() {
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Supplier> getSupplier() {
        return suppliers;
    }

    public void setSupplier(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<ProductType> getType() {
        return types;
    }

    public void setType(List<ProductType> types) {
        this.types = types;
    }



    public void setItems(List<Item> items) {
        this.items = items;
    }

    public HistoricalProductPrice getHistoricalproductprice() {
        return historicalproductprice;
    }

    public void setHistoricalproductprice(HistoricalProductPrice historicalproductprice) {
        this.historicalproductprice = historicalproductprice;
    }
}
