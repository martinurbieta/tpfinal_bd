package com.bd.tpfinal.model;
import javax.persistence.*;



@Entity
@Table(name="item")
public class Item {
    @Id
    @Column(name = "id_item", nullable = false)
    private Long id_item;

    @Column(nullable = false)//Sin GET/SET¿?
    private int quantity;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id_order")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "")
    private Product product;

    public Item() {
    }

    public Long getId_item() {
        return id_item;
    }

    public void setId_item(Long id_item) {
        this.id_item = id_item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
