package com.bd.delivery.model;

import com.bd.tpfinal.model.*;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Cancel extends OrderStatus {

    public Cancel() {}

    public Cancel(Order order) {super(order, "Cancelled");}

    public Cancel(Order order, Date startDate) {super(order, "Cancelled", startDate);}

}