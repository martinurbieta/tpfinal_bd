package com.bd.tpfinal.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
@Document
//@Embeddable

public class Qualification {

    @Field
    private float score;

    @Field
    private String commentary;

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
