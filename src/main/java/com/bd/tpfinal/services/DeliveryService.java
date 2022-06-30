package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * Esta interface define el comportamiento esperado por los servicios relacionados con el sistema.
 *
 * @author Grupo 8
 *
 */
public interface DeliveryService {

    Client newClient(Client client);

    Client editClient(String username, Client client) throws DeliveryException;

    void desactiveClient(String username);

    Client getClientInfo(String username);

 //   List<Order> getClientOrders(String username);

    DeliveryMan newDeliveryMan(DeliveryMan deliveryMan);

    DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException;

    Product editProduct(ObjectId number, Product product)  throws DeliveryException;

    void desactiveDeliveryMan(String username);

    DeliveryMan getDeliveryManInfo(String username);

    List<DeliveryMan> getBestDeliveryMan(Integer Size);

    Order newOrderPending(Map<String, Object> data) throws DeliveryException;

    Order getOrderInfo(ObjectId number);

    List<Order> getOrdersWithMaxItems(String supplier, int size) throws DeliveryException;

    Order getOrderMaxPriceInDate(String date) throws DeliveryException;

    DeliveryMan confirmOrder(ObjectId number) throws DeliveryException;

    Order deliverOrder(ObjectId number) throws DeliveryException;

    Order refuseOrder(ObjectId number) throws DeliveryException;

    Order cancelOrder(ObjectId number) throws DeliveryException;

    Order finishOrder(ObjectId number) throws DeliveryException;

    Order qualifyOrder(ObjectId number, Qualification qualification) throws DeliveryException;

    Order addItem(ObjectId number, Item item) throws DeliveryException;

    Address getAddress(ObjectId id);

    Address createAddress(Address newAddress);

    SupplierType getSupplierTypeById(ObjectId id);

    ProductType getProductTypeById(ObjectId id);

    Product getProductById(ObjectId id);

    List<Supplier> getBestFirstsDispatchersSupplier(Integer quantity);

    Supplier getSupplierById(ObjectId id);

    Supplier createSupplier(Supplier newSupplier);


    Object deleteItem(Item item);

    Item createItem(Item newsItem) throws DeliveryException;

    List<Object> deleteProduct(ObjectId id) throws DeliveryException;

    SupplierType createSupplierType(SupplierType newsSupplierType);

    ProductType createProductType(ProductType newProductType);

    Product createProduct(Product newProduct);


    List<Item> getItemsByOrderNumber(ObjectId number);

    Item getItemWithID(ObjectId id);

    List<Product> getProductBySupplier(ObjectId id);

    List<Product> getProductByProductTypeId(ObjectId id);

    List<ProductType> getProductTypeFindAll();

    List<ProductType> getAllProductTypes();

    @Transactional(readOnly = true)
    List<Supplier> getSupplierByType(ObjectId id);

    List<Supplier> getAllSuppliers();

    List<Product> getAllProducts();

    List<Order> getAllOrders();



    List<HistoricalProductPrice> getHistoricalProductPriceByProductId(ObjectId id);

    List<HistoricalProductPrice> getHistoricalProductPriceBetweenDates(ObjectId number, String startDateStr, String finishDateStr) throws DeliveryException;

    List<ArrayList> getAverageProductTypePrices() throws DeliveryException;

    float getAverageProductTypePrice(ObjectId id) throws DeliveryException;

     List<Supplier> getSupplierProvidingAllProductType();

    List<ArrayList> getSupplierByQualificationValue(Float stars);

    HistoricalProductPrice addPriceToProduct(Long id, Float price) throws DeliveryException;

}
