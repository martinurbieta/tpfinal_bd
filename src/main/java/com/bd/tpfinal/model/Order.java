package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order", unique = true, updatable = false)
    private int number;

    @Column(nullable = false, updatable = false)
    private Date dateOfOrder;

    @Column(length = 500)
    private String comments;

    @Column(nullable = false)
    private float totalPrice;

//    @Embedded
//    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order_status", referencedColumnName = "id") // check "ID"
    private OrderState orderState;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) //check EAGER
    @JoinColumn(name = "id_delivery_man", nullable = true)
    private DeliveryMan deliveryMan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) //check EAGER
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_qualification", referencedColumnName = "id") // check ID.
    private Qualification qualification;

    @JsonIgnore
    @OneToMany(mappedBy = "order_", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Item> items;

    public Order() {
    }

    public Order(Date dateOfOrder, String comments, float totalPrice,Client client) {
        this.dateOfOrder =dateOfOrder;
        this.comments =comments;
        this.totalPrice =totalPrice;
        this.client = client;
        this.deliveryMan = null;
        this.orderState = new Pending(this);
        this.items=new ArrayList<>();
    }

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

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

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
        // agregar método para actualizar proveedor)
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderState getOrderStatus() {
        return orderState;
    }

    public void setOrderStatus(OrderState orderState) {
        this.orderState = orderState;
    }
    /**
     * Adder.
     *
     * @add item to order.
     */
    public void addItem(Item item) throws DeliveryException {
        if (this.getOrderStatus().canAddItem()){
            this.items.add(item);
        }}
    }

