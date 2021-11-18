package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;

import javax.persistence.Embeddable;
import java.util.Date;
@Embeddable
public class Delivered extends OrderStatus {
    public Delivered() { super("Delivered"); }

    public Delivered(OrderStatus orderStatus) {super(orderStatus); }

    public Delivered(Date startDate){
        super("Delivered", startDate);
    }
}
