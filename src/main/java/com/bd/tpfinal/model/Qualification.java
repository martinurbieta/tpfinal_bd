package com.bd.tpfinal.model;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "qualification")
public class Qualification {
    @Id
    @Column(name = "id_qualification", nullable = false)
    private Long id_qualification;


    private float score;

    private String commentary;

    @OneToOne
    private Order order;

    public Qualification() {
    }

    public Long getId_qualification() {
        return id_qualification;
    }

    public void setId_qualification(Long id_qualification) {
        this.id_qualification = id_qualification;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCommentary() {
        return commentary;
    }

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
