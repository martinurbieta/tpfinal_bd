package com.bd.tpfinal.model;


import java.util.Date;

import javax.persistence.Embeddable;

import com.bd.tpfinal.utils.DeliveryException;

@Embeddable
public class Assigned extends OrderStatus {

    public Assigned(){ super("Assigned"); }

    public Assigned(OrderStatus orderStatus) {super(orderStatus); }

    public Assigned(Date startDate){
        super("Assigned", startDate);
    }

    @Override
    public boolean canRefuse() {
        return true;
    }

    @Override
    public boolean canDeliver() {
        return true;
    }

    @Override
    public boolean canCancel() {
        return true;
    }

    @Override
    public void deliver(Order order) throws DeliveryException {
        if(this.canDeliver()) {
            order.setOrderStatus(new Sent());
        } else {
            throw new DeliveryException("The order can't be delivered");
        }
    }
    @Override
    public void refuse(Order order) throws DeliveryException {
        if(this.canRefuse()) {
          order.setOrderStatus(new Pending());
          order.getDeliveryMan().addScore(-2);
          order.getDeliveryMan().setFree(true);
          order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be refused");
        }
    }

    @Override
    public void cancel(Order order) throws DeliveryException {
        if(this.canCancel()){
            order.setOrderStatus(new Cancelled());
            order.getClient().addScore(-1);
            order.getDeliveryMan().setFree(true);
            order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be cancelled");
        }
    }
}
