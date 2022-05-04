package com.bd.tpfinal.model;

import javax.persistence.Embeddable;
import java.util.Date;
@Embeddable
public class Delivered extends OrderStatus {
/*
MARTIN
    public Delivered() {}

    public Delivered(Order order){
        super(order, "Delivered");
    }

    public Delivered(Order order, Date startDate){
        super(order, "Delivered", startDate);
    }

    public Delivered(Order order, Date startDate, boolean cancelledByClient){
        super(order, "Delivered", startDate,cancelledByClient);
    }
*/
    public Delivered() { super("Delivered"); }

    public Delivered(OrderStatus orderStatus) {
        super(orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }

    public Delivered(Date startDate){
        super("Delivered", startDate);
    }

}
