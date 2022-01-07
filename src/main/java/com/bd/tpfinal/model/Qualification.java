package com.bd.tpfinal.model;

public class Qualification {

    @Column(nullable = false, updatable = false)
    private float score;

    @Column(length = 500, updatable=false)
    private String commentary;

    @OneToOne(mappedBy = "qualification")
    private Order order;

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
