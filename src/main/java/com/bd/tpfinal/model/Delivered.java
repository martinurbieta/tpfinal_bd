package com.bd.tpfinal.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Embeddable
@Entity
@Table(name = "order_status")
public class Delivered extends OrderState {

    public Delivered() {}

    public Delivered(Order order){
        super(order, "Delivered");
    }

//    public Delivered(Order order, Date startDate){
//        super(order, "Delivered", startDate);
//    }

}
