package com.bd.tpfinal.model;

import javax.persistence.Embeddable;
import java.util.Date;
@Embeddable
public class Delivered extends OrderStatus {

    public Delivered() { super("Delivered"); }

    public Delivered(OrderStatus orderStatus) {
        super(orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }

    public Delivered(Date startDate){
        super("Delivered", startDate);
    }

}
