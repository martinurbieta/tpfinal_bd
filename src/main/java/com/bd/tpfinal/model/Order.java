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

    @Embedded
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) //check EAGER
    @JoinColumn(name = "id_delivery_man", nullable = true)
    private DeliveryMan deliveryMan;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) //check EAGER
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    @Embedded
    private Qualification qualification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_supplier", referencedColumnName = "id") // check ID.
    private Supplier supplier;

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
        this.orderStatus = new Pending(this);
        this.items=new ArrayList<>();
    }

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public Date getDateOfOrder() {return dateOfOrder;}

    public Supplier getItemProductSupplier(){
        Supplier itemProductSupplier = this.items.get(0).getProductSupplier();
        return itemProductSupplier;
    }

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


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() throws DeliveryException {
        if (!OrderStatus.class.isAssignableFrom(this.orderStatus.getClass())) {
            try {
                String name = this.orderStatus.getName();
                Class<?> cl = Class.forName("com.bd.tpfinal.model." + name);
                this.orderStatus = (OrderStatus) cl.getDeclaredConstructor(OrderStatus.class).newInstance(this.orderStatus);
                return this.orderStatus;
            } catch (Throwable t){
                throw new DeliveryException(t.getMessage());
            }
        } else
            return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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


    public void setQualification(float score, String commentary) throws DeliveryException {
        if (this.getOrderStatus().canQualify()){
            qualification = new Qualification(score,commentary);
            supplier = this.getItemProductSupplier().getName();
            this.getItemProductSupplier().updateScore(qualification);
        }}
    }

