package com.bd.tpfinal.model;

import com.sun.tools.javac.comp.Resolve;

import java.util.List;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier", unique = true, updatable = false)
    private int id;

    @Column(nullable = false, updatable = true, length = 50)
    private String name;

    @Column(nullable = false, updatable = true, length = 13)
    private String cuil;

    @Column(length = 50)
    private String address;

    @Column
    private float[] coords;

    @Column
    private float qualificationOfUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Product> products;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Supplier_SupplierType", 
        joinColumns = { @JoinColumn(name = "id_product") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_supplier_type") }
    )
    private SupplierType supplierType;

    public Supplier(){}

    public Supplier(String name,String cuil, String address, float[] coords,float qualificationOfUsers){
    }
        this.name  = name;
        this.cuil =cuil; //debería ser CUIT.
        this.address = address;
        this.coords= coords
        this.qualificationOfUsers  = qualificationOfUsers;

    /**
     * Getter.
     *
     * @return el nombre del proveedor.
     */

    public String getName() {
        return name;
    }
    /**
     * Setter.
     *
     * @param name es el nombre del proveedor.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return el cuil del proveedor.
     */
    public String getCuil() {
        return cuil;
    }
    /**
     * Setter.
     *
     * @param cuil es el Codigo Unico de Identificción Laboral del proveedor.
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
    /**
     * Getter.
     *
     * @return la dirección del proveedor.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setter.
     *
     * @param address es la dirección del proveedor.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Getter.
     *
     * @return el las coordenadas de la ubicación del proveedor.
     */
    public float[] getCoords() {
        return coords;
    }
    /**
     * Setter.
     *
     * @param coords son las coordenadas de la ubicación del proveedor.
     */
    public void setCoords(float[] coords) {
        this.coords = coords;
    }
    /**
     * Getter.
     *
     * @return las calificaciones emitias por los usuarios del proveedor.
     */
    public float getQualificationOfUsers() {
        return qualificationOfUsers;
    }
    /**
     * Setter.
     *
     * @param qualificationOfUsers es la calificación emitida por los usuarios del proveedor.
     */
    public void setQualificationOfUsers(float qualificationOfUsers) {
        this.qualificationOfUsers = qualificationOfUsers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public SupplierType getSupplierType() {
        return type;
    }

    public void setSupplierType(SupplierType type) {
        this.type = type;
    }
}
