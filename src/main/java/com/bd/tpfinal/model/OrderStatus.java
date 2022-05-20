package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Calendar;
import java.util.Date;

//@Embeddable
@Document
public class OrderStatus {

    @Field
    private String name;

    @Field
    private Date startDate;

    @Field
    private boolean cancelledByClient;

    public OrderStatus(){}

    private void init (String name, Date startDate, boolean cancelledByClient) {
        this.name = name;
        this.startDate = startDate;
        this.cancelledByClient=cancelledByClient;
    }
    public OrderStatus(OrderStatus orderStatus) {
        this.init(orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }

    public OrderStatus(String name) {
        this.init(name, Calendar.getInstance().getTime(), false);
    }

    public OrderStatus(String name, Date startDate) {
        this.init(name, startDate, false);
    }

    public OrderStatus(String name, Date startDate, boolean cancelledByClient) {
        this.init(name, startDate, cancelledByClient);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public boolean getCancelledByClient() {
        return cancelledByClient;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCancelledByClient(boolean cancelledByClient) {
        this.cancelledByClient = cancelledByClient;
    }

    public boolean canAddItem() { return false; }

    public boolean canAssign() { return false; }

    public boolean canRefuse() { return false; }

    public boolean canQualify() { return false; }

    public boolean canDeliver() { return false; }

    public boolean canFinish() { return false; }

    public boolean canCancel() { return false; }

    public void assign(DeliveryMan deliveryMan, Order order) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void refuse(Order order) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void deliver(Order order) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void cancel(Order order) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void finish(Order order) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }
}
