package com.bd.tpfinal.model;

import java.util.List;

public class Supplier {

    @Column(nullable = false, updatable = true, length = 50)
    private String name;

    @Column(nullable = false, updatable = true, length = 13)
    private String cuil;

    @Column(length = 50)
    private String address;

    @Column
    private float[] coords;

    @Column
    private float qualification;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Product> products;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Supplier_SupplierType", 
        joinColumns = { @JoinColumn(name = "id_product") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_supplier_type") }
    )
    private SupplierType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float[] getCoords() {
        return coords;
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    public float getQualificationOfUsers() {
        return qualificationOfUsers;
    }

    public void setQualificationOfUsers(float qualificationOfUsers) {
        this.qualificationOfUsers = qualificationOfUsers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }
}
