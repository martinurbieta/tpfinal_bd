package com.bd.tpfinal.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "historical_product_price")
public class HistoricalProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historical_product_price", unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private float price;

    @Column(nullable = false, updatable = false)
    private Date startDate;

    @Column(nullable = false, updatable = false)
    private Date finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Version
    @Column(name = "version")
    private int version;

    public HistoricalProductPrice(){}

    public HistoricalProductPrice(Product product, float price, Date startDate) {        
        this.product  = product;
        this.price  = price;
        this.startDate =startDate;
    }
    /**
     * Getter.
     *
     * @return el precio del vigente en el periodo entre fechas de inicio y fin..
     */
    public float getPrice() {
        return price;
    }
    /**
     * Setter.
     *
     * @param price es el precio vigente en el periodo entre fechas de inicio y fin.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Getter.
     *
     * @return la fecha de inicio de vigencia del precio.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Setter.
     *
     * @param startDate es la fecha de inicio de vigencia del precio.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * Getter.
     *
     * @return la fecha de final de vigencia del precio.
     */
    public Date getFinishDate() {
        return finishDate;
    }
    /**
     * Setter.
     *
     * @param finishDate es la fecha de fin de vigencia del precio.
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }
}
