package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.utils.DeliveryException;

import java.util.List;


public interface DeliveryService {

    public Client newClient(Client client);

    public Client editClient(String username, Client client) throws DeliveryException;

    public void desactiveClient(String username);

    public Client getClientInfo(String username);

    public Address getAddressWithID(Long id);

//    public List<Order> getClientOrders(String username);

    public DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    public DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    public Product editProduct(Long number, Product product)  throws DeliveryException;

    public void desactiveDeliveryMan(String username);

    public DeliveryMan getDeliveryManInfo(String username);

    public Order newOrderPending(Order order);

    public Order getOrderInfo(Long number);

    public DeliveryMan confirmOrder(Long number) throws DeliveryException;

    public void deliverOrder(Long number) throws DeliveryException;

    public void refuseOrder(Long number) throws DeliveryException;

    public void cancelOrder(Long number) throws DeliveryException;

    public void finishOrder(Long number) throws DeliveryException;

    public void qualifyOrder(Long number, Qualification qualification) throws DeliveryException;



    public Address createAddress(Address address);

    public SupplierType getSupplierTypeById(Long id);

    public ProductType getProductTypeById(Long id);

    public Product getProductById(Long id);

    public SupplierType createSupplierType(SupplierType newsSupplierType);

    public Supplier getSupplier(Long id);

    public Supplier createSupplier(Supplier newsSupplier);

    public ProductType newProductType(ProductType aProductType);

    public Item createItem(Item item);
    
    Object deleteItem(Item item);

    public void deleteProduct(Long id) throws DeliveryException;

    List<Item> getItemsByOrderNumber(Long number);

    public Item getItemWithID(Long id);

    public List<Product> getProductBySupplierId(Long id);

}

