package com.bd.tpfinal.model;

import java.util.List;

public class Product {

    private String name;

    private float price;

    private float weight;

    private String description;

    private Supplier supplier;

    private ProductType type;

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
