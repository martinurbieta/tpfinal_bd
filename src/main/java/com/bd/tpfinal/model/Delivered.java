package com.bd.tpfinal.model;

import java.util.Date;

public class Delivered extends OrderStatus {

    public Delivered() {}

    public Delivered(OrderStatus orderStatus) {
        super(orderStatus.getOrder(), orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }

    public Delivered(Order order){
        super(order, "Delivered");
    }

    public Delivered(Order order, Date startDate){
        super(order, "Delivered", startDate);
    }

}
