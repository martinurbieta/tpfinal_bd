package com.bd.tpfinal.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="type")
public class ProductType {
    @Id
    @Column(name = "id_type", nullable = false)
    private Long id_type;

    private String name;

    private String description;

    @ManyToMany
    private List<Product> products;

    public Long getId_type() {
        return id_type;
    }

    public void setId_type(Long id_type) {
        this.id_type = id_type;
    }

    public ProductType() {
    }

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
