package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.Final_dbException;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Sent extends OrderStatus {
    public Sent() {
    }

    public Sent(Order order) {
        super(order, "Sent");
    }

    public Sent(Order order, Date startDate) {
        super(order, "Sent", startDate);
    }

    public boolean canFinish() {
        return true;
    }

    public void finish() throws Final_dbException {
        this.order.setOrderStatus(new Delivered(this.order));
        this.order.getDeliveryMan().addScore(1);
        this.order.getDeliveryMan().addNumberOfSuccessfulOrders();
        this.order.getClient().addScore(1);
        this.order.getDeliveryMan().setFree(true);
        this.order.getDeliveryMan().deleteOrder(order);
        this.order.setDeliveryMan(null); // Rompo la relacion bidireccional(no hay otra forma en el esquema de db actual)
    }
}
