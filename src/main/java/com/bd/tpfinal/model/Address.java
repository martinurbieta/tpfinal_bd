package com.bd.tpfinal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
public class Address {

    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field
    private String name;

    @Field
    private String address;

    @Field
    private String apartment;

    @Field
    private float coordX;

    @Field
    private float coordY;

    @Field
    private String description;


    @DBRef(lazy = true)
    private Client client;

    @Version
    private int version;

    public Address(){}

    public Address(String name, String address, String apartment, float coordX, float coordY, String description){
        this.name=name;
        this.address=address;
        this.apartment=apartment;
        this.coordX=coordX;
        this.coordY=coordY;
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
     * @return las coordenadas X de la dirección.
     */
    public float getCoordX() {
        return coordX;
    }
    /**
     * Setter.
     *
     * @param coordX es el arreglo de las coordenadas X de la dirección.
     */
    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }
    /**
     * Getter.
     *
     * @return las coordenadas Y de la dirección.
     */
    public float getCoordY() {
        return coordY;
    }
    /**
     * Setter.
     *
     * @param coordY es el arreglo de las coordenadas Y de la dirección.
     */
    public void setCoordY(float coordY) {
        this.coordY = coordY;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ObjectId getId() {
        return id;
    }
}
