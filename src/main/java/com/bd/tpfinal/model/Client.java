package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;



import javax.persistence.*;

@Entity
@Table(name="client")
public class Client extends User{

    @Column
    private Date dateOfRegister;

    @OneToMany(mappedBy ="client", fetch = FetchType.LAZY, cascade = {})
    private List<Order> orders;


    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = {})
    private List<Address> addresses;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Client() {
    }



    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
       this.addresses = addresses;
    }
}
