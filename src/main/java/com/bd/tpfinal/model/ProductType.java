package com.bd.tpfinal.model;

import java.util.List;

public class ProductType {

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(length = 500, updatable=true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product_type", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
