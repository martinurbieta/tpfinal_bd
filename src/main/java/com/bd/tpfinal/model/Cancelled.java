package com.bd.tpfinal.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Cancelled extends OrderStatus {

    public Cancelled() {super("Cancelled");}

    public Cancelled(OrderStatus orderStatus) {
        super(orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
    }
    public Cancelled(Order order, Date startDate) {super("Cancelled", startDate);}

    public Cancelled(Order order, Date startDate, boolean cancelledByClient) {super("Cancelled", startDate, cancelledByClient);}

}