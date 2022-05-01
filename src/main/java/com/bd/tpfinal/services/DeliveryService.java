package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface DeliveryService {

    Client newClient(Client client);

    Client editClient(String username, Client client) throws DeliveryException;

    void desactiveClient(String username);

    Client getClientInfo(String username);

//    List<Order> getClientOrders(String username);

    DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    Product editProduct(Long number, Product product)  throws DeliveryException;

    void desactiveDeliveryMan(String username);

    DeliveryMan getDeliveryManInfo(String username);

    Order newOrderPending(Map<String, Object> data) throws DeliveryException;

    Order getOrderinfo(Long number);

    DeliveryMan confirmOrder(Long number) throws DeliveryException;

    void deliverOrder(Long number) throws DeliveryException;

    void refuseOrder(Long number) throws DeliveryException;

    void cancelOrder(Long number) throws DeliveryException;

    void finishOrder(Long number) throws DeliveryException;

    void qualifyOrder(Long number, Qualification qualification) throws DeliveryException;

    Address getAddress(Long id);

    Address createAddress(Address newAddress);

    SupplierType getSupplierType(Long id);

    SupplierType createSupplierType(SupplierType newsSupplierType);

    Supplier getSupplier(Long id);

    Supplier createSupplier(Map<String, Object> data);
    
    Object deleteItem(Item item);

    Item createItem(Item newsItem);

    void deleteProduct(Long id) throws DeliveryException;

    Product createProduct(Map<String, Object> data);

    ProductType createProductType(ProductType newProductType);

    List<Item> getItemsByOrderNumber(Long number);

    Item getItemWithID(Long id);

    List<Product> getProductBySupplier(Long id);

}