package com.bd.tpfinal.model;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {
    @Id
    @Column(name = "id_order", nullable = false)
    private int number;

    @Column(nullable = false, updatable = false)
    private Date dateOfOrder;

    @Column(length = 500)
    private String comments;

    private float totalPrice;

    @Embedded
    private OrderStatus status;

    @Column(nullable = false)
    private float coordX;

    @Column(nullable = false)
    private float coordY;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_man_id_user")
    private DeliveryMan deliveryMan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id_user")
    private Client client;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "address_id_address")
    private Address address;

    @OneToOne
    private Qualification qualification;

    // @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = {})
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {}, orphanRemoval = true)
    private List<Item> items;

    @Column(nullable = false)
    private float priceProducts;

    @Embedded
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(Date dateOfOrder, Address address, String comments, float coordX, float coordY,  float priceProducts, Client client, List<Item> items){
        this.dateOfOrder = dateOfOrder;
        this.address = address;
        this.comments = comments;
        this.coordX = coordX;
        this.coordY = coordY;
        this.priceProducts = priceProducts;
        this.client = client;
        this.deliveryMan = null;
        this.orderStatus = new Pending(this);
        this.items = items;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getComments() {
        return comments;
    }

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

    public void setAddress(Address   address) {
        this.address = address;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
