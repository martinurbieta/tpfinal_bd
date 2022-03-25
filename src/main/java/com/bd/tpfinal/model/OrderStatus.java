package com.bd.tpfinal.model;

import javax.persistence.*;
import com.bd.tpfinal.utils.DeliveryException;
import java.util.Calendar;
import java.util.Date;

@Embeddable
public abstract class OrderStatus {

    @Column(name = "state")
    private String name;

    @Column(name = "state_start_date")
    private Date startDate;

    @Column(name = "cancelled_by_client")
    private boolean cancelledByClient;

    @Transient
    public Order order;

    public OrderStatus(){}

    private void init (Order order, String name, Date startDate, boolean cancelledByClient) {
        this.name = name;
        this.order = order;
        this.startDate = startDate;
        this.cancelledByClient=cancelledByClient;
    }
    public OrderStatus(Order order, String name) {
        this.init(order, name, Calendar.getInstance().getTime(), false);
    }

    public OrderStatus(Order order, String name, Date startDate) {
        this.init(order, name, startDate, false);
    }

    public OrderStatus(Order order, String name, Date startDate, boolean cancelledByClient) {
        this.init(order, name, startDate, cancelledByClient);
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
