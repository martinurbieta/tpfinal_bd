package com.bd.tpfinal.model;

import java.util.List;

public class SupplierType {

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(length = 500, updatable=true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier_type", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Supplier> suppliers;

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

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
