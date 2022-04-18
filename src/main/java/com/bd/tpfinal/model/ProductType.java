package com.bd.tpfinal.model;

import javax.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product_type", unique = true, updatable = false)
    private int id;

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(length = 500, updatable=true)
    private String description;

    @Version
    @Column(name = "version")
    private int version;

    public ProductType(){}

    public ProductType(String name, String description){

        this.name = name;
        this.description = description;
    }
    /**
     * Getter.
     *
     * @return el nombre del tipo de producto.
     */

    public String getName() {
        return name;
    }
    /**
     * Setter.
     *
     * @param name es el nombre del tipo de producto.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return la descripción del tipo de producto.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
