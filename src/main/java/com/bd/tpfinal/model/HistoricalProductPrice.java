package com.bd.tpfinal.model;

import java.util.Date;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "historicalProductPrice")
public class HistoricalProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historicalProductPrice", unique = true, updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    private float price;

    @Column(nullable = false, updatable = false)
    private Date startDate;

    @Column(nullable = false, updatable = false)
    private Date finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public HistoricalProductPrice(){}

    public HistoricalProductPrice(float price, Date startDate,Date finishDate){
    }
        this.price  = price;
        this.startDate =startDate
        this.finishDate = finishDate;

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
}
