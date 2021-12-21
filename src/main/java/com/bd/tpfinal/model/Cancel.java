package com.bd.tpfinal.model;

import java.util.Date;

public class Cancelled extends OrderStatus{

    public Cancelled() {}

    public Cancelled(Order order) {super(order, "Cancelled");}

    public Cancelled(Order order, Date startDate) {super(order, "Cancelled", startDate);}

}
