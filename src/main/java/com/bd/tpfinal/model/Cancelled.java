package com.bd.tpfinal.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import javax.persistence.Embeddable;

@Document
//@Embeddable
public class Cancelled extends OrderStatus {
    public Cancelled() { super("Cancelled"); }

    public Cancelled(Date startDate) {super("Cancelled", startDate);}

    public Cancelled(Date startDate, boolean cancelledByClient) {super("Cancelled", startDate, cancelledByClient);}

}
