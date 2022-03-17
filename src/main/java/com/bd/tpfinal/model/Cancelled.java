package com.bd.tpfinal.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Cancelled extends OrderStatus {

    public Cancelled() {}

    public Cancelled(OrderStatus orderStatus) {
        super(orderStatus.getOrder(), orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }
    public Cancelled(Order order) {super(order, "Cancelled");}

    public Cancelled(Order order, Date startDate) {super(order, "Cancelled", startDate);}

    public Cancelled(Order order, Date startDate, boolean cancelledByClient) {super(order, "Cancelled", startDate, cancelledByClient);}

}