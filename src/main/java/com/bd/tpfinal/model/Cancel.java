package com.bd.tpfinal.model;

public class Cancel extends OrderStatus{

    private boolean cancelledByClient;

    public boolean isCancelledByClient() {
        return cancelledByClient;
    }

    public void setCancelledByClient(boolean cancelledByClient) {
        this.cancelledByClient = cancelledByClient;
    }
}
