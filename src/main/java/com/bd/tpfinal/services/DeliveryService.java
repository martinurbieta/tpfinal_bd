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

 //   List<Order> getClientOrders(String username);

    DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    Product editProduct(Long number, Product product)  throws DeliveryException;

    void desactiveDeliveryMan(String username);

    DeliveryMan getDeliveryManInfo(String username);

//    public Order newOrderPending(Order order);

    Order newOrderPending(Map<String, Object> data) throws DeliveryException;

    Order getOrderInfo(Long number);

    DeliveryMan confirmOrder(Long number) throws DeliveryException;

    void deliverOrder(Long number) throws DeliveryException;

    void refuseOrder(Long number) throws DeliveryException;

    void cancelOrder(Long number) throws DeliveryException;

    void finishOrder(Long number) throws DeliveryException;

    void qualifyOrder(Long number, Qualification qualification) throws DeliveryException;

    Address getAddress(Long id);

    Address createAddress(Address newAddress);

    SupplierType getSupplierTypeById(Long id);

    ProductType getProductTypeById(Long id);

    Product getProductById(Long id);


    Supplier getSupplierById(Long id);
// BLAS
 //   Supplier createSupplier(Map<String, Object> data);

    Supplier createSupplier(Supplier newSupplier);


    Object deleteItem(Item item);

    Item createItem(Item newsItem);

    void deleteProduct(Long id) throws DeliveryException;
  //BLAS
  //  Product createProduct(Map<String, Object> data);

    SupplierType createSupplierType(SupplierType newsSupplierType);

    ProductType createProductType(ProductType newProductType);

    Product createProduct(Product newProduct);


    List<Item> getItemsByOrderNumber(Long number);

    Item getItemWithID(Long id);

    List<Product> getProductBySupplier(Long id);

    List<HistoricalProductPrice> getHistoricalProductPriceByProductId(Long id);

    List<HistoricalProductPrice> getHistoricalProductPriceBetweenDates(Map<String, Object> data);

}