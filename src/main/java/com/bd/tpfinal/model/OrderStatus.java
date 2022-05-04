package com.bd.tpfinal.model;

import javax.persistence.*;
import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Parent;

import java.util.Calendar;
import java.util.Date;

@Embeddable
public class OrderStatus {
//public class OrderStatus {


    @Column(name = "state")
    private String name;

    @Column(name = "state_start_date")
    private Date startDate;

    @Column(name = "cancelled_by_client")
    private boolean cancelledByClient;

    @Transient
    @JsonIgnore
    @Parent
    public Order order;

    public OrderStatus(){}

/*
MARTIN
    public OrderStatus(String name) {
        this.name = name;
        this.order = order;
        this.startDate = Calendar.getInstance().getTime();
        this.cancelledByClient=this.getCancelledByClient();
    }

    public OrderStatus(String name, Date startDate){
        this.name = name;
        this.order = order;
        this.startDate = startDate;
        this.cancelledByClient=this.getCancelledByClient();
    }

    public OrderStatus(String name, Date startDate,boolean cancelledByClient){
        this.name = name;
        this.order = order;
        this.startDate = startDate;
        this.cancelledByClient=cancelledByClient;
    }
*/
    private void init (String name, Date startDate, boolean cancelledByClient) {
        this.name = name;
        this.startDate = startDate;
        this.cancelledByClient=cancelledByClient;
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

    public void assign(DeliveryMan deliveryMan) throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void refuse() throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void deliver() throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void cancel() throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }

    public void finish() throws DeliveryException{
        throw new DeliveryException("No se puede realizarse esta accion");
    }
}
