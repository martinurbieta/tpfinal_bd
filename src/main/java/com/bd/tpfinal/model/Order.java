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
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "id_order")
    private List<Item> items;

    public Order() {
    }

    public Order(Date dateOfOrder, String comments, float totalPrice,Client client) throws DeliveryException {
        this.dateOfOrder =dateOfOrder;
        this.comments =comments;
        this.totalPrice =totalPrice;
        this.client = client;
        this.deliveryMan = null;
        this.orderStatus = new Pending();
        this.items=new ArrayList<>();
        this.address=null;
    }

    public Long getNumber() {return number;}

    public void setNumber(Long number) {this.number = number;}

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
                this.orderStatus = (OrderStatus) cl.getDeclaredConstructor(OrderStatus.class).newInstance(orderStatus);
                return this.orderStatus;
            } catch (Throwable t) {
                throw new DeliveryException(t.getMessage());
            }
        } else
            return this.orderStatus;
    }
}
