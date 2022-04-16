package com.bd.tpfinal.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="supplier")
public class Supplier {
    @Id
    @Column(name = "id_supplier", nullable = false)
    private Long id_supplier;

    private String name;

    private String cuil;

    private String address;

    //private float[] coords;

    private float qualificationOfUsers;

    @ManyToMany
    private List<Product> products;

    @ManyToMany
    private List<SupplierType> types;

    public Supplier() {
    }

    public Long getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Long id_supplier) {
        this.id_supplier = id_supplier;
    }

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

   /* public float[] getCoords() {
        return coords;
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }
*/
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

    public List<SupplierType> getTypes() {
        return types;
    }

    public void setType(List<SupplierType> types) {
        this.types = types;
    }





}
