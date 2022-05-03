package com.bd.tpfinal.model;


import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.bd.tpfinal.utils.DeliveryException;
import org.hibernate.annotations.Parent;

@Embeddable
public class Assigned extends OrderStatus {

    public Assigned() {
    }

    public Assigned(Order order) {
        super(order, "Assigned");
    }

    public Assigned(Order order, Date startDate) {
        super(order, "Assigned", startDate);
    }

    public Assigned(Order order, Date startDate, boolean cancelledByClient) {
        super(order, "Assigned", startDate, cancelledByClient);
    }

//    public Assigned(OrderStatus orderStatus) {
//        super(orderStatus.getName(), orderStatus.getStartDate(), orderStatus.getCancelledByClient());
//    }
//    public Assigned(){
//        super("Assigned");
//    }
//
//    public Assigned(Date startDate){
//        super("Assigned", startDate);
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
            this.order.setOrderStatus(new Sent());
        } else {
            throw new DeliveryException("The order can't be delivered");
        }
    }
    @Override
    public void refuse() throws DeliveryException {
        if(this.canRefuse()) {
    //  BLAS      this.order.setOrderStatus(new Cancelled());
            this.order.setOrderStatus(new Cancelled(this.order));
            this.order.getDeliveryMan().addScore(-2);
    //        this.order.getDeliveryMan().deleteOrder(order); En solución FEDERICO
            this.order.getDeliveryMan().setFree(true);
            this.order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be refused");
        }
    }

    @Override
    public void cancel() throws DeliveryException {
        if(this.canCancel()){
    // BLAS        this.order.setOrderStatus(new Cancelled());
            this.order.setOrderStatus(new Cancelled(this.order));
    //        this.order.getDeliveryMan().deleteOrder(order); En solución FEDERICO
            this.order.getClient().addScore(-1);
            this.order.getDeliveryMan().setFree(true);
            this.order.setDeliveryMan(null);
        } else {
            throw new DeliveryException("The order can't be cancelled");
        }
    }
}
