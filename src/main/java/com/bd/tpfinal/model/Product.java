package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Document
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Product {

    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field
    private String name;

    @Field
    private float price;

    @Field
    private float weight;

    @Field
    private String description;

    @DBRef
    @BsonIgnore
    private Supplier supplier;

    @Version
    private int version;

    @BsonIgnore
    @DBRef
    private List<ProductType> productType;
    
    public Product(){}

    public Product(String name, float price, float weight, String description, Supplier supplier){
        this.name = name;
        this.price=price;
        this.weight=weight;
        this.description = description;
        this.productType=new ArrayList<>();
        this.supplier = supplier;
    }
    /**
     * Getter.
     *
     * @return el id del producto.
     */
    public ObjectId getId() {
        return this.id;
    }
    /**
     * Getter.
     *
     * @return el nombre del producto.
     */
    public String getName() {
        return name;
    }
    /**
     * Setter.
     *
     * @param name es el nombre del producto.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return el precio del producto.
     */
    public float getPrice() {
        return price;
    }
    /**
     * Setter.
     *
     * @param price es el precio del producto.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Getter.
     *
     * @return el peso del producto.
     */
    public float getWeight() {
        return weight;
    }
    /**
     * Setter.
     *
     * @param weight es el peso del producto.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
    /**
     * Getter.
     *
     * @return la descripción del producto.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<ProductType> getType() {
        return productType;
    }

    public void setProductType(List<ProductType> productType) {
        this.productType = productType;
    }

    public void addProductType(ProductType productType) {this.productType.add(productType);}

    public void removeProductType(ProductType productType) {this.productType.remove(productType);}


    public List<ProductType> getProductType() {
        return this.productType;
    }

    public void setPrices(List<ProductType> productType) {
        this.productType = productType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void update(Product product) {
        this.setName(product.getName());
        this.setWeight(product.getWeight());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setSupplier(product.getSupplier());
        this.setPrice(product.getPrice());
    }
}
