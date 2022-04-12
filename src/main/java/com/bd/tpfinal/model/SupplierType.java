package com.bd.tpfinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "supplier_type")
public class SupplierType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier_type", unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, length = 50, updatable=true)
    private String name;

    @Column(length = 500, updatable=true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "supplierType", fetch = FetchType.LAZY, orphanRemoval = false) //before supplier_type
    private List<Supplier> suppliers;

    @Version
    @Column(name = "version")
    private int version;

    public SupplierType(){}

    public SupplierType(String name,String description){

        this.name  = name;
        this.description = description;
    }
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
