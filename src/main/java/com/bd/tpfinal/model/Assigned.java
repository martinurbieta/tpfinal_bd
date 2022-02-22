package com.bd.tpfinal.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bd.tpfinal.utils.DeliveryException;

//@Embeddable
@Entity
@Table(name = "order_status")
public class Assigned extends OrderState {

    public Assigned() {}

    public Assigned(Order order){
        super(order, "Assigned");
    }

//    public Assigned(Order order, Date startDate){
//        super(order, "Assigned", startDate);
//    }

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
    public boolean canAddItem() {
        return false;
    }

    @Override
    public void deliver() throws DeliveryException {
        if(this.canDeliver()) {
            this.order.setOrderStatus(new Sent(this.order));
        } else {
            throw new DeliveryException("The order can't be delivered");
        }
    }
    @Override
    public void refuse() throws DeliveryException {
        if(this.canRefuse()) {
            this.order.setOrderStatus(new Cancel(this.order));
            this.order.getDeliveryMan().addScore(-2);
            this.order.getDeliveryMan().removeOrder(order);
            this.order.getDeliveryMan().setFree(true);
            this.order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be refused");
        }
    }

    @Override
    public void cancel() throws DeliveryException {
        if(this.canCancel()){
            this.order.setOrderStatus(new Cancel(this.order));
            this.order.getDeliveryMan().removeOrder(order);
            this.order.getClient().addScore(-1);
            this.order.getDeliveryMan().setFree(true);
            this.order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be cancelled");
        }
    }
}
