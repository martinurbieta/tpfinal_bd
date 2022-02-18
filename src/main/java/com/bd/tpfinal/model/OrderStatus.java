package com.bd.tpfinal.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

//@Embeddable
@Entity
@Table(name = "order_status")
public abstract class OrderStatus {

    @Column(name = "state")
    private String name;

    @Column(name = "state_start_date")
    private Date startDate;

    @Column(name = "cancelled_by_client")
    private boolean cancelledByClient;

    @Transient
    private Order order;

    public OrderStatus(){}

    public OrderStatus(Order order, String name) {
        this.name = name;
        this.order = order;
        this.startDate = Calendar.getInstance().getTime();
        this.cancelledByClient=false;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean canAddItem() { return false; }

    public boolean canAssign() { return false; }

    public boolean canRefuse() { return false; }

    public boolean canDeliver() { return false; }

    public boolean canFinish() { return false; }

    public boolean canCancel() { return false; }

    public boolean addItem() throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    public boolean assign(DeliveryMan deliveryMan) throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    public boolean refuse() throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    public boolean deliver() throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    public boolean cancel() throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    public boolean finish() throws Exception{
        throw new Exception("No se puede realizarse esta accion");
    }

    //
//    /*
//     * Debido a la incompatibilidad de Hibernet y JPA con embebeber la clases hijas, una solucion es instanciar
//     * el estado de manera manual.
//     * La clase que se recupera, si bien es un OrderStatus, no es una clase concreta.
//     */
//    public void setStatusByName(){
//        switch (orderStatus.getName()){
//            case "Pending":
//                this.setOrderStatus(new Pending(this, this.orderStatus.getStartDate()));
//                break;
//            case "Assigned":
//                this.setOrderStatus(new Assigned(this, this.orderStatus.getStartDate()));
//                break;
//            case "Sent":
//                this.setOrderStatus(new Sent(this, this.orderStatus.getStartDate()));
//                break;
//            case "Delivered":
//                this.setOrderStatus(new Delivered(this, this.orderStatus.getStartDate()));
//                break;
//            case "Cancel":
//                this.setOrderStatus(new Cancel(this, this.orderStatus.getStartDate()));
//                break;
//        }
//    }

}
