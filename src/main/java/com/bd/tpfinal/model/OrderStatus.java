package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.Final_dbException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Embeddable
public abstract class OrderStatus {


    @Column(name = "state", insertable = false, updatable = false)
    private String name;

    @Column(name = "state_start_date", insertable = false, updatable = false)
    protected Date startDate;

    @Transient
    protected Order order;

    public OrderStatus(){}

    public OrderStatus(Order order, String name) {
        this.name = name;
        this.order = order;
        this.startDate = Calendar.getInstance().getTime();
    }

    public OrderStatus(Order order, String name, Date startDate){
        this.name = name;
        this.order = order;
        this.startDate = startDate;
    }

    public OrderStatus(Order order) {

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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean canAddItem() { return false; }

    public boolean canAssign() { return false; }

    public boolean canRefuse() { return false; }

    public boolean canDeliver() { return false; }

    public boolean canFinish() { return false; }

    public boolean canCancel() { return false; }

    public boolean addItem() throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public void assign(DeliveryMan deliveryMan) throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public boolean refuse() throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public void deliver() throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public void cancel() throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public void finish() throws Final_dbException{
        throw new Final_dbException("No se puede realizarse esta accion");
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
