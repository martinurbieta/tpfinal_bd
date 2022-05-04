package com.bd.tpfinal.controller;

import com.bd.tpfinal.model.*;
import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping(path = "/item")
    public Item createItem(@RequestBody Item newItem){
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
/*
    @PostMapping(path = "/order")
    public Order newOrder(@RequestBody Order order){
        return this.service.newOrderPending(order);
    }
*/
    @GetMapping(path = "/order/{number}/items")
    public List<Item> getItemsByOrderID(@PathVariable Long number){
        return this.service.getItemsByOrderNumber(number);
    }

    @GetMapping(path = "/order/{number}")
    public Order getOrder(@PathVariable Long number){
        return this.service.getOrderInfo(number);
    }

    @PutMapping(path = "/order/{number}/refuse")
    public void refuseOrder(Long number) throws DeliveryException {
        this.service.refuseOrder(number);
    }

    @PutMapping(path = "/order/{number}/cancel")
    public void cancelOrder(Long number) throws DeliveryException{
        this.service.cancelOrder(number);
    }

    @PutMapping(path = "/order/{number}/confirm")
    public DeliveryMan confirmOrder(@PathVariable Long number) throws DeliveryException{
        return this.service.confirmOrder(number);
    }

    @PutMapping(path = "/order/{number}/finish")
    public void finishOrder(Long number) throws DeliveryException {
        this.service.finishOrder(number);
    }

    @PostMapping(path = "/order/{number}/qualify")
    public void qualifyOrder(Long number, @RequestBody Qualification qualification) throws DeliveryException {
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


    @DeleteMapping(path = "/product/{id}")
    public void deleteProduct(@PathVariable Long id) throws DeliveryException{
        this.service.deleteProduct(id);
    }
//    BLAS
//    @PostMapping(path = "/product")
//    public Product createProduct(@RequestBody Map<String, Object> data) {
//        return this.service.createProduct(data);
//    }




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
// BLAS
//    @PostMapping(path = "/supplier")
//    public Supplier createSupplier(@RequestBody Map<String, Object> data){
//        return this.service.createSupplier(data);
//    }

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

    @GetMapping(path = "/supplier/{id}")  // tested_ok
    public Supplier getSupplierById(@PathVariable Long id){
        return this.service.getSupplierById(id);
    }
    @GetMapping(path = "/product/{id}")  // tested_ok
    public Product getProductById(@PathVariable Long id){
        return this.service.getProductById(id);
    }


}
