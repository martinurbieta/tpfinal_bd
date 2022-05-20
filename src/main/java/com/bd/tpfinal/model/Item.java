package com.bd.tpfinal.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.*;

@Document
public class Item {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field
    private int quantity;

    @Field
    private String description;

    @DBRef
    private Order order;

  //  @JsonIgnore
    @DBRef
    private Product product;

    @Version
    private int version;

    public Item() {}

    public Item(int quantity, String description, Product product) {
        this.quantity = quantity;
        this.description = description;
        this.product = product;
    }
    /**
     * Getter.
     *
     * @return el producto.
     */
    public Supplier getProductSupplier() {
        Supplier productSupplier= this.product.getSupplier();
        return productSupplier;
    }
    /**
     * Getter.
     *
     * @return la cantidad del item.
     */
    public int getQuantity() {
        return this.quantity;
    }
    /**
     * Setter.
     *
     * @param quantity es la cantidad del item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Getter.
     *
     * @return la descripción del item.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order  = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
