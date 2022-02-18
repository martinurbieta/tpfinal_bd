package com.bd.tpfinal.model;

import com.bd.tpfinal.model.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

//@Embeddable
@Entity
@Table(name = "order_status")
public class Cancel extends OrderStatus {

    public Cancel() {}

    public Cancel(Order order) {super(order, "Cancelled");}

    public Cancel(Order order, Date startDate) {super(order, "Cancelled", startDate);}

}