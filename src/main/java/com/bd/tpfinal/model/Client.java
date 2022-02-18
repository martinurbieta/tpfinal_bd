package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
public class  Client extends User{

    @Column(updatable = false, nullable = false)
    private Date dateOfRegister;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Address> addresses;

    public Client(){}

    public Client(String name, String email, String username, String password, Date dateOfBirth) {
        super(name, email, username, password, dateOfBirth);
        this.dateOfRegister = Calendar.getInstance().getTime();
        this.orders = new ArrayList<>();
    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addOrder(Order order) { this.orders.add(order); }

}
