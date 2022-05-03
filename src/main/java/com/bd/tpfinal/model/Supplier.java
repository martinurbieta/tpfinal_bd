package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier", unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = true, length = 50)
    private String name;

    @Column(nullable = false, updatable = true, length = 13)
    private String cuit;

    @Column(length = 50)
    private String address;

    @Column(name = "coord_x")
    private float coordX;

    @Column(name = "coord_y")
    private float coordY;

    @Column
    private float qualification;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE}) // cambiado de ALL a MERGE según stackoverflow. https://stackoverflow.com/questions/13370221/persistentobjectexception-detached-entity-passed-to-persist-thrown-by-jpa-and-h
    @JoinColumn(name = "id_supplier_type", nullable = false)
    private SupplierType supplierType;

    @Version
    @Column(name = "version")
    private int version;

    public Supplier(){}

    public Supplier(String name,String cuit, String address, float coordX, float coordY,float qualification,SupplierType supplierType){

        this.name  = name;
        this.cuit =cuit;
        this.address = address;
        this.coordX=coordX;
        this.coordY=coordY;
        this.qualification  = qualification;
        this.supplierType= supplierType;
    }
    /**
     * Getter.
     *
     * @return el id del proveedor.
     */

    public Long getId() {
        return this.id;
    }
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
     * @return el cuit del proveedor.
     */
    public String getCuit() {
        return cuit;
    }
    /**
     * Setter.
     *
     * @param cuit es el Codigo Unico de Identificción Laboral del proveedor.
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
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
     * @return las coordenadas X de la dirección del proveedor.
     */
    public float getCoordX() {
        return coordX;
    }
    /**
     * Setter.
     *
     * @param coordX es el arreglo de las coordenadas X de la dirección del proveedor.
     */
    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }
    /**
     * Getter.
     *
     * @return las coordenadas Y de la dirección del proveedor.
     */
    public float getCoordY() {
        return coordY;
    }
    /**
     * Setter.
     *
     * @param coordY es el arreglo de las coordenadas Y de la dirección del proveedor.
     */
    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }
    /**
     * Getter.
     *
     * @return la descripción de la dirección.
     */
    /**
     * Getter.
     *
     * @return las calificaciones emitias por los usuarios del proveedor.
     */
    public float getQualification() {
        return qualification;
    }
    /**
     * Setter.
     *
     * @param qualification es la calificación emitida por los usuarios del proveedor.
     */
    public void setQualification(float qualification) {
        this.qualification = qualification;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public SupplierType getSupplierType() {
        return supplierType;
    }
    /**
     * Setter.
     *
     * @param aSupplierType es el nombre del tipo de Proveedor.
     */
    public void setSupplierType(SupplierType aSupplierType) {
        this.supplierType = aSupplierType;
    }
    /**
     * Updater.
     *
     * @Return actualiza la calificación de usuarios.
     */
    //   agregar método para actualizar la calificación de proveedor.

    /**
     * Remover.
     *
     * @param product a remover del listado de productos.
     */
    public void removeProduct(Product product){
        this.products.remove(product);

    }

    /**
     * Adder.
     *
     * @param product a agergar del listado de productos.
     */
    public void addProduct(Product product){
        this.products.add(product);

    }

    public void updateScore(Qualification aNewQualification, Long qualifications){
        float newAverageScore = (qualifications*this.getQualification()+aNewQualification.getScore())/(qualifications+1);
        this.setQualification(newAverageScore);
    }

}
