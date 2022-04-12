package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier", unique = true, updatable = false)
    private long id;

    @Column(nullable = false, updatable = true, length = 50)
    private String name;

    @Column(nullable = false, updatable = true, length = 13)
    private String cuil;

    @Column(length = 50)
    private String address;

    @Column
    private float coordX;

    @Column
    private float coordY;

    @Column
    private float qualificationOfUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_supplier_type", nullable = false)
    private SupplierType supplierType;

    @Version
    @Column(name = "version")
    private int version;

//    @JsonIgnore
//    @OneToMany(mappedBy = "qualification", fetch = FetchType.LAZY, orphanRemoval = false)
//    private List<Qualification> qualifications;

    public Supplier(){}

    public Supplier(String name,String cuil, String address, float coordX, float coordY,float qualificationOfUsers,SupplierType supplierType){

        this.name  = name;
        this.cuil =cuil; //debería ser CUIT.
        this.address = address;
        this.coordX=coordX;
        this.coordY=coordY;
        this.qualificationOfUsers  = qualificationOfUsers;
        this.supplierType= supplierType;
    //    this.qualifications = new ArrayList<>();
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



//    public void updateScore(Qualification aNewQualification){
//        this.qualifications.add(aNewQualification);
//
//        double newAverageScore = qualifications.stream()
//                .mapToDouble(Qualification::getScore)
//                .average()
//                .orElse(0.0);
//        newAverageScore=(float)newAverageScore;
//        this.setQualificationOfUsers(newAverageScore);
//    }

}