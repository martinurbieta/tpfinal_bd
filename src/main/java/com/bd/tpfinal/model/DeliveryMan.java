package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "delivery_man")
public class DeliveryMan extends User{

    @Column(nullable = false)
    private int numberOfSuccess;

    @Column
    private boolean free;

    @Column(nullable = false, updatable = false)
    private Date dateOfAdmission;

//    @Column(nullable = false, updatable = false)
//    private Order orderAssigned;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryMan", fetch = FetchType.LAZY) //antes delivery_man
    private List<Order> actualOrders;



    public DeliveryMan(){}

    public DeliveryMan(String name, String email, String username, String password, Date dateOfBirth) {
        super(name, email, username, password, dateOfBirth);
        this.numberOfSuccess = 0;
        this.free = true;
        this.dateOfAdmission = Calendar.getInstance().getTime();
        this.actualOrders = new ArrayList<>();
    }
    public int getNumberOfSuccess() {
      return numberOfSuccess;
    }

    public void setNumberOfSuccess(int numberOfSuccess) {
        this.numberOfSuccess = numberOfSuccess;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public List<Order> getActualOrders() {
        return actualOrders;
    }

//    public void setOrdersPending(List<Order> ordersPending) {
//        this.ordersPending = ordersPending;
//    }
    public void setActualOrders(List<Order> actualOrders) {
        this.actualOrders = actualOrders;
    }

    public void addNumberOfSuccess(){ this.numberOfSuccess++; }

    /**
     * Adder.
     *
     * @add order to delivery man.
     */
    public void addOrder(Order order) {this.actualOrders.add(order);}

    /**
     * Remover.
     *
     * @remove order from delivery man collection.
     */
    public void removeOrder(Order order) {this.actualOrders.remove(order);}


}
