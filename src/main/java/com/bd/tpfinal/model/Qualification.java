package com.bd.tpfinal.model;

import javax.persistence.*;

@Embeddable

public class Qualification {

    @Column(nullable = false, updatable = false)
    private float score;

    @Column(length = 500, updatable=false)
    private String commentary;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_order", nullable = true)
    private Order order;

//
    public Qualification(){}

    public Qualification(float score, String commentary){

        this.score = score;
        this.commentary  = commentary;
    }
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

//    public Order getOrder() {
//        return this.order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
}
