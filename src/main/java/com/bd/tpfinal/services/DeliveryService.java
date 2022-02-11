package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import com.bd.tpfinal.utils.DeliveryException;
import java.util.List;

public interface DeliveryService {

    public Client newClient(Client client);

    public Client editClient(String username, Client client) throws DeliveryException;

    public void desactiveClient(String username);

    public Client getClientInfo(String username);

    public List<Order> getClientOrders(String username);

    public DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    public DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    public void desactiveDeliveryMan(String username);

    public DeliveryMan getDeliveryManInfo(String username);

    public Order newOrderPending(Order order);

    public Order getOrderinfo(long number);

    public boolean assignOrder(long number);

    public List<Order> getAssignedOrders(String username);

    public void acceptOrder(long number) throws DeliveryException;

    public void refuseOrder(long number) throws DeliveryException;

    public void cancelOrder(long number) throws DeliveryException;

    public void finishOrder(long number) throws DeliveryException;

}

