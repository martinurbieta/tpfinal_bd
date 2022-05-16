package com.bd.tpfinal.controller;

import com.bd.tpfinal.model.*;
import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT ,RequestMethod.DELETE})
public class DeliveryController {

    @Autowired
    public DeliveryService service;

    @GetMapping("/test")
    public String test(){
        return "OK!";
    }
    @GetMapping(path = "/address/{id}") //tested_ok
    public Address getAddress(@PathVariable Long id){
        return this.service.getAddress(id);
    }

    @PostMapping(path = "/address")  //tested_ok
    public Address createAddress(@RequestBody Address newAddress){
        return this.service.createAddress(newAddress);
    }

    @PostMapping(path = "/client") //tested_ok
    public Client newClient(@RequestBody Client client){
        return this.service.newClient(client);
    }

    @PutMapping(path = "/client/{username}") //tested_ok
    public Client editClient(@RequestBody Client client, @PathVariable String username) throws DeliveryException{
        return this.service.editClient(username, client);
    }

    @DeleteMapping(path = "/client/{username}") //tested_ok
    public void desactiveClient(@PathVariable String username){
        this.service.desactiveClient(username);
    }

    @GetMapping(path = "/client/{username}") //tested_ok
    public Client getClient(@PathVariable String username){
        return this.service.getClientInfo(username);
    }

    /*    @GetMapping(path = "/client/{username}/orders")
        public List<Order> getClientOrders(@PathVariable String username){
            return this.service.getClientOrders(username);
        }
    */
    @PostMapping(path = "/deliveryMan") //tested_ok
    public DeliveryMan newDeliveryMan(@RequestBody DeliveryMan deliveryMan){
        return this.service.newDeliveryMan(deliveryMan);
    }

    @PutMapping(path = "/deliveryMan/{username}")  //tested_ok
    public DeliveryMan editDeliveryMan(@PathVariable String username, @RequestBody DeliveryMan deliveryMan) throws DeliveryException{
        return this.service.editDeliveryMan(username, deliveryMan);
    }

    @DeleteMapping(path = "/deliveryMan/{username}")  //tested_ok
    public void desactiveDeliveryMan(@PathVariable String username){
        this.service.desactiveDeliveryMan(username);
    }

    @GetMapping(path = "/deliveryMan/{username}")   //tested_ok
    public DeliveryMan getDeliveryMan(@PathVariable String username){
        return this.service.getDeliveryManInfo(username);
    }

    @GetMapping(path = "/deliveryMan/bestTen")   //tested_ok
    public List<DeliveryMan> getBestTenDeliveryMan(){
        return this.service.getBestDeliveryMan(10);
    }
    @PostMapping(path = "/item")
    public Item createItem(@RequestBody Item newItem) throws DeliveryException {
        return this.service.createItem(newItem);
    }

    @DeleteMapping(path = "/item/{id}")
    public Object deleteItem(@PathVariable Long id){
        return this.service.deleteItem(this.service.getItemWithID(id));
    }
    @PostMapping(path = "/order")
    public Order newOrder(@RequestBody Map<String, Object> data) throws DeliveryException {
        return this.service.newOrderPending(data);
    }

    @GetMapping(path = "/order/maxItems/supplier/{supplier}/firsts/{size}")
    public List<Order> getOrdersWithMaxItems(@PathVariable Long supplier, @PathVariable int size) throws DeliveryException {
        return this.service.getOrdersWithMaxItems(supplier, size);
    }

    @GetMapping(path = "/order/maxItems/supplier/{supplier}")
    public List<Order> getOrderWithMaxItems(@PathVariable Long supplier) throws DeliveryException {
        return this.service.getOrdersWithMaxItems(supplier, 1);
    }

    @GetMapping(path = "/order/maxTotalPrice/inDate/{date}")
    public Order getOrderWithMaxItems(@PathVariable String date) throws DeliveryException {
        return this.service.getOrderMaxPriceInDate(date);
    }
    @GetMapping(path = "/order/{number}/items")
    public List<Item> getItemsByOrderID(@PathVariable Long number){
        return this.service.getItemsByOrderNumber(number);
    }

    @GetMapping(path = "/order/{number}")
    public Order getOrder(@PathVariable Long number){
        return this.service.getOrderInfo(number);
    }

    @PutMapping(path = "/order/{number}/refuse")
    public void refuseOrder(@PathVariable Long number) throws DeliveryException {
        this.service.refuseOrder(number);
    }

    @PutMapping(path = "/order/{number}/cancel")
    public void cancelOrder(@PathVariable Long number) throws DeliveryException{
        this.service.cancelOrder(number);
    }

    @PutMapping(path = "/order/{number}/confirm")
    public DeliveryMan confirmOrder(@PathVariable Long number) throws DeliveryException{
        return this.service.confirmOrder(number);
    }
    @PutMapping(path = "/order/{number}/deliver")
    public void deliverOrder(@PathVariable Long number) throws DeliveryException{
        this.service.deliverOrder(number);
    }
    @PutMapping(path = "/order/{number}/finish")
    public void finishOrder(@PathVariable Long number) throws DeliveryException {
        this.service.finishOrder(number);
    }

    @PostMapping(path = "/order/{number}/qualify")
    public void qualifyOrder(@PathVariable Long number, @RequestBody Qualification qualification) throws DeliveryException {
        this.service.qualifyOrder(number, qualification);
    }

    @PutMapping(path = "/product/{id}")  // ok
    public Product editProduct(@PathVariable Long id, @RequestBody Product product) throws DeliveryException{
        return this.service.editProduct(id, product);
    }

    @GetMapping(path = "/product/bySupplier/{id}")
    public List<Product> getProductBySupplier(@PathVariable Long id){
        return this.service.getProductBySupplier(id);

    }
    @GetMapping(path = "/product/producttype/{id}")
    public List<Product> getProductByProductTypeId(@PathVariable Long id){
        return this.service.getProductByProductTypeId(id);
    }

    @DeleteMapping(path = "/product/{id}")
    public void deleteProduct(@PathVariable Long id) throws DeliveryException{
        this.service.deleteProduct(id);
    }

    @GetMapping(path = "/supplierType/{id}")  // tested_ok
    public SupplierType getSupplierTypeById(@PathVariable Long id){
        return this.service.getSupplierTypeById(id);
    }

    @PostMapping(path = "/supplierType")   // tested_ok
    public SupplierType createSupplierType(@RequestBody SupplierType newsSupplierType){
        return this.service.createSupplierType(newsSupplierType);
    }

    @PostMapping(path = "/productType")  // tested_ok
    public ProductType createProductType(@RequestBody ProductType newProductType) {
        return this.service.createProductType(newProductType);
    }

    @PostMapping(path = "/supplier")  // tested_ok
    public Supplier createSupplier(@RequestBody Supplier newSupplier) {
        return this.service.createSupplier(newSupplier);
    }

    @PostMapping(path = "/product")  // tested_ok
    public Product createProduct(@RequestBody Product newProduct) {
        return this.service.createProduct(newProduct);
    }


    @GetMapping(path = "/productType/{id}") // tested_ok
    public ProductType getProductTypeById(@PathVariable Long id){
        return this.service.getProductTypeById(id);
    }

    @GetMapping(path = "/supplier/byTpe/{id}")
    public List<Supplier> getSupplierByType(@PathVariable Long id){
        return this.service.getSupplierByType(id);

    }
    @GetMapping(path = "/supplier/{id}")  // tested_ok
    public Supplier getSupplierById(@PathVariable Long id){
        return this.service.getSupplierById(id);
    }

    @GetMapping(path = "/supplier/bestTenDispatchers")
    public List<Supplier> getBestTenDispatchersSupplier() throws DeliveryException {
        return this.service.getBestFirstsDispatchersSupplier(10);
    }

    @GetMapping(path = "/product/{id}")  // tested_ok
    public Product getProductById(@PathVariable Long id){
        return this.service.getProductById(id);
    }

    @GetMapping(path = "/product/{id}/historicalPrice") // tested_ok
    public List<HistoricalProductPrice> getHistoricalProductPriceByProductId(@PathVariable Long id){
        return this.service.getHistoricalProductPriceByProductId(id);
    }

    @GetMapping(path = "/product/{id}/historicalPrice/betweenDates/{startDateStr}/{finishDateStr}")
    public List<HistoricalProductPrice> getHistoricalProductPriceBetweenDates(@PathVariable Long id, @PathVariable String startDateStr, @PathVariable String finishDateStr) throws DeliveryException {
        return this.service.getHistoricalProductPriceBetweenDates(id, startDateStr, finishDateStr);

    }
    @GetMapping(path = "/product/averagePrices/for/allTypes")
    public List<ArrayList> getAverageProductTypePrices() throws DeliveryException {
        return  this.service.getAverageProductTypePrices();
    }

    @GetMapping(path = "/product/averagePrices/for/{id}")
    public float getAverageProductTypePrice(@PathVariable Long id) throws DeliveryException {
        return this.service.getAverageProductTypePrice(id);
    }


    @GetMapping(path = "/productType/all")
    public List<ProductType> getProductTypeFindAll(){
        return this.service.getProductTypeFindAll();
    }

    @GetMapping(path = "/supplier/allProductTypes")
    public List<Supplier> getSupplierProvidingAllProductType(){
        return this.service.getSupplierProvidingAllProductType();
    }

    @GetMapping(path = "/supplier/qualification/hasAtLeast/{stars}")
    public List<Supplier>  getSupplierByQualificationValue(@PathVariable Integer stars){
        return this.service.getSupplierByQualificationValue(stars);
    }

}

