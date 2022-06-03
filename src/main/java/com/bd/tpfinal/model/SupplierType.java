package com.bd.tpfinal.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Document
public class SupplierType {

    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field
    private String name;

    @Field
    private String description;

    @Version
    private int version;

    public SupplierType(){}

    public SupplierType(String name,String description){

        this.name  = name;
        this.description = description;
    }
    /**
     * Getter.
     *
     * @return el nombre del tipo de proveedor.
     */

    public String getName() {
        return name;
    }
    /**
     * Setter.
     *
     * @param name es el nombre del tipo de proveedor.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return la descripción del tipo de proveedor.
     */

    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del tipo de proveedor.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
