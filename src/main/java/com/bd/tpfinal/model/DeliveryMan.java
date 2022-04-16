package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="deliveryman")
public class DeliveryMan extends User{

    @Column(nullable = false)
    private int numberOfSuccessOrders;

    @Column
    private boolean free;

    @Column(nullable = false, updatable = false)
    private Date dateOfAdmission;


    @JsonIgnore
    @OneToMany(mappedBy = "deliveryMan", fetch = FetchType.LAZY)
    private List<Order> actualOrders;



    public DeliveryMan() {

    }

    public DeliveryMan(String name, String email, String username, String password, Date dateOfBirth) {
        super(name, email, username, password, dateOfBirth);
        this.numberOfSuccessOrders = 0;
        this.free = true;
        this.dateOfAdmission = Calendar.getInstance().getTime();
        this.actualOrders = new ArrayList<>();
    }



    public int getNumberOfSuccessOrders() {
        return numberOfSuccessOrders;
    }

    public void setNumberOfSuccessOrders(int numberOfSuccessOrders) {
        this.numberOfSuccessOrders = numberOfSuccessOrders;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void deleteOrder(Order order) { this.actualOrders.remove(order); }

    public void addNumberOfSuccessfulOrders(){ this.numberOfSuccessOrders++;}

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public void addOrder(Order order) {
        this.actualOrders.add(order);
    }



}
