package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.util.Date;
@Document
//@Embeddable
public class Delivered extends OrderStatus {
    public Delivered() { super("Delivered"); }

    public Delivered(OrderStatus orderStatus) {super(orderStatus); }

    public Delivered(Date startDate){
        super("Delivered", startDate);
    }
}
