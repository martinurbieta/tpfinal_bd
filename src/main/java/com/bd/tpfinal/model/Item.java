package com.bd.tpfinal.model;
import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item", unique = true, updatable = false)
    private int id;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public Item(){}

    public Item(int quantity, String description, Product product){

        this.quantity = quantity;
        this.description = description;
        this.product = product;
    }

    /**
     * Getter.
     *
     * @return el producto.
     */
    public Supplier getProductSupplier() {
        Supplier productSupplier= this.product.getSupplier()
        return productSupplier;
    }
    /**
     * Getter.
     *
     * @return la cantidad del item.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Setter.
     *
     * @param quantity es la cantidad del item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Getter.
     *
     * @return la descripción del item.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del item.
     */
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
