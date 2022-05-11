package com.bd.tpfinal.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Cancelled extends OrderStatus {
    public Cancelled() { super("Cancelled"); }

    public Cancelled(Date startDate) {super("Cancelled", startDate);}

    public Cancelled(Date startDate, boolean cancelledByClient) {super("Cancelled", startDate, cancelledByClient);}

}
