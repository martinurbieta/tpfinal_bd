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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Document
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class HistoricalProductPrice {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field
    private float price;

    @Field
    private Date startDate;

    @Field
    private Date finishDate;

    @DBRef
    @BsonIgnore
    private Product product;

    @Version

    private int version;

    public HistoricalProductPrice(){}

    public HistoricalProductPrice(Product product, float price, Date startDate) {        
        this.product  = product;
        this.price  = price;
        this.startDate =startDate;
    }
    /**
     * Getter.
     *
     * @return el precio del vigente en el periodo entre fechas de inicio y fin..
     */
    public float getPrice() {
        return price;
    }
    /**
     * Setter.
     *
     * @param price es el precio vigente en el periodo entre fechas de inicio y fin.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Getter.
     *
     * @return la fecha de inicio de vigencia del precio.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Setter.
     *
     * @param startDate es la fecha de inicio de vigencia del precio.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Getter.
     *
     * @return la fecha de final de vigencia del precio.
     */
    public Date getFinishDate() {
        return finishDate;
    }
    /**
     * Setter.
     *
     * @param finishDate es la fecha de fin de vigencia del precio.
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Product getProduct() {
        return product;
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