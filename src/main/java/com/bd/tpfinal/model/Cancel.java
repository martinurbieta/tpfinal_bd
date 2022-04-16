package com.bd.tpfinal.model;

import java.util.Date;

public class Cancel extends OrderStatus{

    public Cancel() {}

    public Cancel(Order order) {super(order, "Cancelled");}

    public Cancel(Order order, Date startDate) {super(order, "Cancelled", startDate);}

    private boolean cancelledByClient;

    public boolean isCancelledByClient() {
        return cancelledByClient;
    }

    public void setCancelledByClient(boolean cancelledByClient) {
        this.cancelledByClient = cancelledByClient;
    }
}
