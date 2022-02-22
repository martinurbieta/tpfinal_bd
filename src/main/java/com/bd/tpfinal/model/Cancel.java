package com.bd.tpfinal.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Embeddable
@Entity
@Table(name = "order_status")
public class Cancel extends OrderState {

    public Cancel() {}

    public Cancel(Order order) {super(order, "Cancelled");}

//    public Cancel(Order order, Date startDate) {super(order, "Cancelled", startDate);}

}