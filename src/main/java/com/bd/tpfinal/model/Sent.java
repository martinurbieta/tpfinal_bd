package com.bd.tpfinal.model;

import javax.persistence.Embeddable;

import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//@Embeddable
@Document
public class Sent extends OrderStatus {

    public Sent() {super("Sent"); }

    public Sent(OrderStatus orderStatus){
        super(orderStatus);
    }

    public Sent( Date startDate, boolean cancelledByClient){ 
        super("Sent", startDate,cancelledByClient);  
    }

    @Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public boolean canQualify() {
        return true;
    }

    @Override
    public void finish(Order order) throws DeliveryException {
        order.setOrderStatus(new Delivered());
        order.getDeliveryMan().addScore(1);
        order.getDeliveryMan().addNumberOfSuccess();
        order.getClient().addScore(1);
        order.getDeliveryMan().setFree(true);
        order.setDeliveryMan(null); // Rompo la relacion bidireccional(no hay otra forma en el esquema de db actual)
    }
}
