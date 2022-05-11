package com.bd.tpfinal.model;

import com.bd.tpfinal.utils.DeliveryException;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Pending extends OrderStatus {
    public Pending() { super("Pending"); }
    public Pending(OrderStatus orderStatus) {super(orderStatus); }
    public Pending(Date startDate) {super("Pending", startDate); }

    @Override
    public boolean canAssign() {
        return true;
    }

    @Override
    public boolean canAddItem() {
        return true;
    }

    @Override
    public boolean canCancel() {
        return true;
    }

    @Override
    public void assign(DeliveryMan deliveryMan, Order order) throws DeliveryException {
        if (this.canAssign()) {
            deliveryMan.setFree(false);
            order.setDeliveryMan(deliveryMan);
            order.setOrderStatus(new Assigned());
        } else {
            throw new DeliveryException("The order can't be assigned to the delivery man");
        }
    }

    @Override
    public void cancel(Order order) throws DeliveryException {
        if (this.canCancel()) {
           order.setOrderStatus(new Cancelled());
//           order.getClient().addScore(0); // Pending no penaliza al cliente. Un pedido esta confirmado cuando esta confirmado cuando se asigna repartirdor (segundo bullet)."Resta un punto cuando cancela uno ya confirmado y asignado".
        } else {
            throw new DeliveryException("The order can't be cancelled");
        }
    }


}
