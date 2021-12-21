package com.bd.tpfinal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", unique = true, updatable = false)
    private int number;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String apartment;

    @Column(length = 500)
    private float[] coords;

    @Column(length = 500)
    private String description;


    private Client client;

    private List<Order> orders;

    public Address(){}

    public Address(String name, String address, String apartment, float coordX, float coordY, String description){
        this.name=name;
        this.address=address;
        this.apartment=apartment;
        this.coords=[coordX,coordY]; // new array?
        this.description=description;
    }
    /**
     * Getter.
     *
     * @return el nombre de la dirección.
     */
    public String getName() {return this.name;}
    /**
     * Setter.
     *
     * @param name es el nombre de la dirección.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return el domicilio dirección.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Setter.
     *
     * @param address es el domicilio de la dirección.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Getter.
     *
     * @return el departamento de la dirección.
     */
    public String getApartment() {
        return apartment;
    }
    /**
     * Setter.
     *
     * @param apartment es el departamento de la dirección.
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
    /**
     * Getter.
     *
     * @return las coordenadas de la dirección.
     */
    public float[] getCoords() {
        return coords;
    }
    /**
     * Setter.
     *
     * @param coords es el arreglo de las coordenadas X, Y de la dirección.
     */
    public void setCoords(float[] coords) {
        this.coords = coords;
    }
    /**
     * Getter.
     *
     * @return la descripción de la dirección.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción de la dirección.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    /**
     * Getter.
     *
     * @return una colección que contiene los las órdenes de la dirección.
     */
    public List<Order> getOrders() {
        return orders;
    }
    /**
     * Setter.
     *
     * @param orders una colección que contiene las órdenes de la dirección.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
