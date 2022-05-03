package com.bd.tpfinal.model;

import javax.persistence.Embeddable;

import com.bd.tpfinal.utils.DeliveryException;

import java.util.Date;

@Embeddable
public class Sent extends OrderStatus {

//    public Sent() { super("Sent"); }  //BLAS

    public Sent() {}

    public Sent(Order order){
        super(order, "Sent");
    }

    public Sent(Order order, Date startDate){
        super(order, "Sent", startDate);
    }

    public Sent(Order order, Date startDate, boolean cancelledByClient){
        super(order, "Sent", startDate,cancelledByClient);
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
    public void finish() throws DeliveryException {
// Blas        this.order.setOrderStatus(new Delivered());
        this.order.setOrderStatus(new Delivered(this.order));
        this.order.getDeliveryMan().addScore(1);
        this.order.getDeliveryMan().addNumberOfSuccess();
        this.order.getClient().addScore(1);
        this.order.getDeliveryMan().setFree(true);
//        this.order.getDeliveryMan().deleteOrder(order); solucioń Federico
        this.order.setDeliveryMan(null); // Rompo la relacion bidireccional(no hay otra forma en el esquema de db actual)
    }
}
