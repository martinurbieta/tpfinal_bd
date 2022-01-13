package com.bd.tpfinal.model;

import java.util.List;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "qualification")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_qualification", unique = true, updatable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    private float score;

    @Column(length = 500, updatable=false)
    private String commentary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_qualification", nullable = false)
    private Order order;

    public Qualification(){}

    public Qualification(float score, String commentary){
    }
        this.score = score;
        this.commentary  = commentary;

    /**
     * Getter.
     *
     * @return el puntaje de la calificación.
     */

    public float getScore() {
        return score;
    }
    /**
     * Setter.
     *
     * @param score es el puntaje de la calificación.
     */
    public void setScore(float score) {
        this.score = score;
    }
    /**
     * Getter.
     *
     * @return el comentario de la calificación.
     */
    public String getCommentary() {
        return commentary;
    }
    /**
     * Setter.
     *
     * @param commentary es el comentario de la calificación.
     */
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
