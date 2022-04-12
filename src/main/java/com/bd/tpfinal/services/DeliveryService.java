package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.transaction.annotation.Transactional;

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

    public void deliverOrder(long number) throws DeliveryException;

    public void refuseOrder(long number) throws DeliveryException;

    public void cancelOrder(long number) throws DeliveryException;

    public void finishOrder(long number) throws DeliveryException;

    public Address getAddress(int id_address);

    public Address createAddress(Address newAddress);

    public SupplierType getSupplierType(Long id_supplier_type);

    public SupplierType createSupplierType(SupplierType newsSupplierType);

    public Supplier getSupplier(Long id_supplier);

    public Supplier createSupplier(Supplier newsSupplier);

    public Item createItem(Item newsItem);
    
    Object deleteItem(Item item);

    List<Item> getItemsByOrderID(Long id);

    public Item findItemWithID(int idItem);
}

