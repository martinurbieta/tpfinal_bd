package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


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

    public Order getOrderinfo(long number);

    public DeliveryMan confirmOrder(long number) throws DeliveryException;

    public void deliverOrder(long number) throws DeliveryException;

    public void refuseOrder(long number) throws DeliveryException;

    public void cancelOrder(long number) throws DeliveryException;

    public void finishOrder(long number) throws DeliveryException;

    public void qualifyOrder(long number, Qualification qualification) throws DeliveryException;

    public Address getAddress(long id);

    public Address createAddress(Address address);

    public SupplierType getSupplierTypeById(Long id);

    public ProductType getProductTypeById(Long id);

    public Product getProductById(Long id);

    public SupplierType createSupplierType(SupplierType newsSupplierType);

    public Supplier getSupplier(Long id);

    public Supplier createSupplier(Map<String, Object> data);

    Object deleteItem(Item item);

    public Item createItem(Item newsItem);

    public void deleteProduct(Long id) throws DeliveryException;

    Product createProduct(Map<String, Object> data);

    public ProductType createProductType(ProductType newProductType);

    List<Item> getItemsByOrderNumber(Long number);

    public Item getItemWithID(Long id);

    public List<Product> getProductBySupplierId(Long id);

}

