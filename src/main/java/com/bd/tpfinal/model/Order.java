package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id_order", unique = true, updatable = false)
    private Long number;

    @Column(nullable = false, updatable = false)
    private Date dateOfOrder;

    @Column(length = 500)
    private String comments;

    @Column(nullable = false)
    private float totalPrice;

    @Embedded
    private OrderStatus orderStatus;

    @Embedded
    private Qualification qualification;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}) //check EAGER
    @JoinColumn(name = "id_delivery_man")
    private DeliveryMan deliveryMan;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @Version
    @Column(name = "version")
    private int version;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_order")
    private List<Item> items;

    public Order() {
    }

    public Order(Date dateOfOrder, String comments, float totalPrice,Client client) {
        this.dateOfOrder =dateOfOrder;
        this.comments =comments;
        this.totalPrice =totalPrice;
        this.client = client;
        this.deliveryMan = null;
//        this.orderStatus = new Pending(this);
        this.orderStatus = new Pending();  //BLAS
        this.items=new ArrayList<>();
        this.address=null;
    }

    public Long getNumber() {return number;}

    public void setNumber(Long number) {this.number = number;}

    public Date getDateOfOrder() {return dateOfOrder;}

//    public Supplier getItemProductSupplier(){
//        return this.items.get(0).getProductSupplier();
//    }

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
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
        } else {
            throw new DeliveryException("En el estado actual no puede agregar items");
        }
    }

    public void setQualification(Qualification qualification) throws DeliveryException {
        if (this.getOrderStatus().canQualify()){
            this.qualification = qualification;
        } else {
            throw new DeliveryException("En el estado actual no puede calificar");
        }
    }
    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public OrderStatus getOrderStatus() throws DeliveryException {
        if (this.orderStatus.getClass().getName().equals("com.bd.tpfinal.model.OrderStatus")) {
            try {
                String name = this.orderStatus.getName();
                Class<?> cl = Class.forName("com.bd.tpfinal.model." + name);
                String orderStatusName=this.orderStatus.getName();
                this.orderStatus = (OrderStatus) cl.getDeclaredConstructor(OrderStatus.class).newInstance(this.orderStatus);
                return this.orderStatus;
            } catch (Throwable t) {
                throw new DeliveryException(t.getMessage());
            }
        } else
            return this.orderStatus;
    }

    // FEDERICO
/*
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    /*
     * Debido a la incompatibilidad de Hibernet y JPA con embebeber la clases hijas, una solucion es instanciar
     * el estado de manera manual.
     * La clase que se recupera, si bien es un OrderStatus, no es una clase concreta.
     */
/*
    public void setStatusByName(){
        switch (orderStatus.getName()){
            case "Pending":
                this.setOrderStatus(new Pending(this, this.orderStatus.getStartDate(),this.orderStatus.getCancelledByClient()));
                break;
            case "Assigned":
                this.setOrderStatus(new Assigned(this, this.orderStatus.getStartDate(),this.orderStatus.getCancelledByClient()));
                break;
            case "Sent":
                this.setOrderStatus(new Sent(this, this.orderStatus.getStartDate(),this.orderStatus.getCancelledByClient()));
                break;
            case "Delivered":
                this.setOrderStatus(new Delivered(this, this.orderStatus.getStartDate(),this.orderStatus.getCancelledByClient()));
                break;
            case "Cancelled":
                this.setOrderStatus(new Cancelled(this, this.orderStatus.getStartDate(),this.orderStatus.getCancelledByClient()));
                break;
        }
    }
*/
}
