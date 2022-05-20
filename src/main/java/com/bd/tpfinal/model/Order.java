package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.lang.Nullable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Order {

    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId number;

    @Field
    private Date dateOfOrder;

    @Field
    private String comments;

    @Field
    private float totalPrice;

    @Field
    private OrderStatus orderStatus;

    @Field
    private Qualification qualification;

    @Field
    private Address address;

    @Field
    private DeliveryMan deliveryMan;

    @Field
    private Client client;

    @DBRef
    private Supplier supplier;

    @Version
    private int version;

    @BsonIgnore
    @DBRef
    private List<Item> items;

    public Order() {
    }

    public Order(Date dateOfOrder, String comments, float totalPrice,Client client) throws DeliveryException {
        this.dateOfOrder =dateOfOrder;
        this.comments =comments;
        this.totalPrice =totalPrice;
        this.client = client;
        this.deliveryMan = null;
        this.orderStatus = new Pending();
        this.items=new ArrayList<>();
        this.address=null;
    }

    public ObjectId getNumber() {return number;}

    public void setNumber(ObjectId number) {this.number = number;}

    public Date getDateOfOrder() {return dateOfOrder;}

    public void setDateOfOrder(Date dateOfOrder) {this.dateOfOrder = dateOfOrder;}

    public String getComments() {return comments;}

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Qualification getQualification() {
        return qualification;
    }




    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }





    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    /**
     * Adder.
     *
     * @add item to order.
     */
    public void addItem(Item item) throws DeliveryException {
        if (this.getOrderStatus().canAddItem()){
            this.items.add(item);
        } else {
            throw new DeliveryException("En el estado actual no puede agregar items");
        }
    }

    public void setQualification(Qualification qualification) throws DeliveryException {
        if (this.getOrderStatus().canQualify()){
            this.qualification = qualification;
        } else {
            throw new DeliveryException("En el estado actual no puede calificar");
        }
    }
    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public OrderStatus getOrderStatus() throws DeliveryException {
        if (this.orderStatus.getClass().getName().equals("com.bd.tpfinal.model.OrderStatus")) {
            try {
                String name = this.orderStatus.getName();
                Class<?> cl = Class.forName("com.bd.tpfinal.model." + name);
                this.orderStatus = (OrderStatus) cl.getDeclaredConstructor(OrderStatus.class).newInstance(orderStatus);
                return this.orderStatus;
            } catch (Throwable t) {
                throw new DeliveryException(t.getMessage());
            }
        } else
            return this.orderStatus;
    }
}
