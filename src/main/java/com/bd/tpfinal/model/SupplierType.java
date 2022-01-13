package com.bd.tpfinal.model;

import java.util.List;
import java.util.List;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "supplierType")
public class SupplierType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplierType", unique = true, updatable = false)
    private int id;

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(length = 500, updatable=true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier_type", fetch = FetchType.LAZY, orphanRemoval = false) //debería ser ManyToMany
    private List<Supplier> suppliers;

    public SupplierType(){}

    public SupplierType(String name,String description){
    }
        this.name  = name;
        this.description = description;

    /**
     * Getter.
     *
     * @return el nombre del tipo de proveedor.
     */

    public String getName() {
        return name;
    }
    /**
     * Setter.
     *
     * @param name es el nombre del tipo de proveedor.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter.
     *
     * @return la descripción del tipo de proveedor.
     */

    public String getDescription() {
        return description;
    }
    /**
     * Setter.
     *
     * @param description es la descripción del tipo de proveedor.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
