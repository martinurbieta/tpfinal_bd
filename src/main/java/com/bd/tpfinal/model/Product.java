package com.bd.tpfinal.model;

import java.util.List;

public class Product {

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(nullable = false, updatable=true)
    private float price;

    @Column(updatable=true)
    private float weight;

    @Column(length = 500, updatable=true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supplier", nullable = false)
    private Supplier supplier;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Product_ProductType", 
        joinColumns = { @JoinColumn(name = "id_product") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_product_type") }
    )
    private List<ProductType> types;

    private List<HistoricalProductPrice> prices;

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<HistoricalProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<HistoricalProductPrice> prices) {
        this.prices = prices;
    }
}
