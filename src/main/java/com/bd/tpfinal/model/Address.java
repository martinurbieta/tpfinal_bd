package com.bd.tpfinal.model;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", nullable = false)
    private Long id_address;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String apartment;

    @Column
    private float cordX;

    @Column
    private float cordY;

    public float getCordX() {
        return cordX;
    }

    public void setCordX(float cordX) {
        this.cordX = cordX;
    }

    public float getCordY() {
        return cordY;
    }

    public void setCordY(float cordY) {
        this.cordY = cordY;
    }

    @Column
    private String description;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name = "client_id_user", nullable = false)
    private Client client;


    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = {}, orphanRemoval = false)
    private List<Order> orders;


    public Address()
    {

    }

    public Address(String name, String address, String apartment, String description, Float cordX, Float cordY)
    {
        this.name = name;
        this.address = address;
        this.apartment = apartment;
        this.cordX = cordX;
        this.cordY = cordY;
        this.description = description;
    }

    public Long getId_address() {
        return id_address;
    }

    public void setId_address(Long id_address) {
        this.id_address = id_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
       return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
