package com.bd.tpfinal.model;

import java.util.List;

public class Supplier {

    private String name;

    private String cuil;

    private String address;

    private float[] coords;

    private float qualificationOfUsers;

    private List<Product> products;

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
