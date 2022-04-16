package com.bd.tpfinal.model;


import java.util.Date;
import com.bd.tpfinal.utils.Final_dbException;

public class Assigned extends OrderStatus{

    public Assigned() {}

    public Assigned(Order order) {

        super(order, "Assigned");

    }

    public Assigned(Order order, Date startDate){
        super(order, "Assigned", startDate);
    }


    public boolean canRefuse() {
        return true;
    }

    public boolean canDeliver() {
        return true;
    }

    public boolean canCancel() {
        return true;
    }

    public void deliver() throws Final_dbException {
        if(this.canDeliver()) {
            this.order.setOrderStatus(new Sent(this.order));
        } else {
            throw new Final_dbException("The order can't be delivered");
        }


    }
}
