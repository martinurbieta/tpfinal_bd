package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.Final_dbException;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Pending extends OrderStatus{

    public Pending() {
    }
    public Pending(Order order) {
        super(order, "Pending");
    }

    public Pending(Order order, Date startDate) {
        super(order, "Pending", startDate);
    }



    public boolean canAssigned() {
        return true;
    }

    @Override
    public boolean canCancel() {
        return true;
    }

    @Override
    public void assign(DeliveryMan deliveryMan) throws Final_dbException {
        if (this.canAssigned()) {
            deliveryMan.addOrder(this.order);
            deliveryMan.setFree(false);
            this.order.setDeliveryMan(deliveryMan);
            this.order.setOrderStatus(new Assigned(this.order));
        } else {
            throw new Final_dbException("The order can't be assigned");
        }
    }

    @Override
    public void cancel() throws Final_dbException {
        if (this.canCancel()) {
            this.order.setOrderStatus(new Cancel(this.order));
            this.order.getClient().addScore(-1);
        } else {
            throw new Final_dbException("The order can't be cancelled");
        }
    }

}
