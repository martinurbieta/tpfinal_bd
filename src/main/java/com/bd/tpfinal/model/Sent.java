package com.bd.tpfinal.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bd.tpfinal.utils.DeliveryException;

@Embeddable
public class Sent extends OrderStatus {

    public Sent() {}

    public Sent(Order order){
        super(order, "Sent");
    }

//    public Sent(Order order, Date startDate){
//        super(order, "Sent", startDate);
//    }
@Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public void finish() throws DeliveryException {
        this.order.setOrderStatus(new Delivered(this.order));
        this.order.getDeliveryMan().addScore(1);
        this.order.getDeliveryMan().addNumberOfSuccess();
        this.order.getClient().addScore(1);
        this.order.getDeliveryMan().setFree(true);
        this.order.getDeliveryMan().removeOrder(order);
        this.order.setDeliveryMan(null); // Rompo la relacion bidireccional(no hay otra forma en el esquema de db actual)
    }
}
