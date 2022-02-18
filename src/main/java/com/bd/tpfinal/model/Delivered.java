package com.bd.tpfinal.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

//@Embeddable
@Entity
@Table(name = "order_status")
public class Delivered extends OrderStatus{

    public Delivered() {}

    public Delivered(Order order){
        super(order, "Delivered");
    }

    public Delivered(Order order, Date startDate){
        super(order, "Delivered", startDate);
    }

}
