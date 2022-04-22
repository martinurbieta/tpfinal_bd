package com.bd.tpfinal.controller;

import com.bd.tpfinal.model.*;
import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// https://go.postman.co/workspace/Team-Workspace~9df97e40-07a0-45e0-8a84-fb11ba783d14/collection/20436870-42d92330-e996-4970-bb71-fa18e2a0d0c1?action=share&creator=20436870
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



    @PostMapping(path = "/address")  //ok
    public Address createAddress(@RequestBody Address address){
        return this.service.createAddress(address);
    }

    @PostMapping(path = "/client") //ok
    public Client newClient(@RequestBody Client client){
        return this.service.newClient(client);
    }

    @PutMapping(path = "/client/{username}") //ok
    public Client editClient(@RequestBody Client client, @PathVariable String username) throws DeliveryException{
        return this.service.editClient(username, client);
    }


    @DeleteMapping(path = "/client/{username}") //ok
    public void desactiveClient(@PathVariable String username){
        this.service.desactiveClient(username);
    }

    @GetMapping(path = "/client/{username}") //ok
    public Client getClient(@PathVariable String username){
        return this.service.getClientInfo(username);
    }

    @GetMapping(path = "/address/{id}")  // ok
    public Address getAddress(@PathVariable Long id){ return this.service.getAddressWithID(id);
    }

/*    @GetMapping(path = "/client/{username}/orders")
    public List<Order> getClientOrders(@PathVariable String username){
        return this.service.getClientOrders(username);
    }
*/
    @PostMapping(path = "/deliveryMan") //ok
    public DeliveryMan newDeliveryMan(@RequestBody DeliveryMan deliveryMan){
        return this.service.newDeliveryMan(deliveryMan);
    }

    @PutMapping(path = "/deliveryMan/{username}")  // ok
    public DeliveryMan editDeliveryMan(@PathVariable String username, @RequestBody DeliveryMan deliveryMan) throws DeliveryException{
        return this.service.editDeliveryMan(username, deliveryMan);
    }

    @DeleteMapping(path = "/deliveryMan/{username}") // ok
    public void desactiveDeliveryMan(@PathVariable String username){
        this.service.desactiveDeliveryMan(username);
    }

    @GetMapping(path = "/deliveryMan/{username}") // ok
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
    public Order newOrder(@RequestBody Order order){
        return this.service.newOrderPending(order);
    }

    @GetMapping(path = "/order/{items}")
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

    @GetMapping(path = "/product/bySupplier/{id}") //ok
    public List<Product> getProductBySupplier(@PathVariable Long id){
        return this.service.getProductBySupplierId(id);
    }

    @DeleteMapping(path = "/product/{id}") //ok
    public void deleteProduct(@PathVariable Long id) throws DeliveryException{
        this.service.deleteProduct(id);
    }

    @GetMapping(path = "/supplierType/{id}")  // ok
    public SupplierType getSupplierTypeById(@PathVariable Long id){
        return this.service.getSupplierTypeById(id);
    }

    @PostMapping(path = "/supplierType")  // ok
    public SupplierType createSupplierType(@RequestBody SupplierType newsSupplierType){
        return this.service.createSupplierType(newsSupplierType);
    }

    @GetMapping(path = "/supplier/{id}") //ok
    public Supplier getSupplier(@PathVariable Long id){
        return this.service.getSupplier(id);
    }

    @PostMapping(path = "/supplier")  // ok
    public Supplier createSupplier(@RequestBody Supplier newsSupplier){
        return this.service.createSupplier(newsSupplier);
    }

    @PostMapping(path = "/productType")
    public ProductType newProductType(@RequestBody ProductType aProductType){
        return this.service.newProductType(aProductType);
    }

    @GetMapping(path = "/productType/{id}")  //ok
    public ProductType getProductTypeById(@PathVariable Long id){
        return this.service.getProductTypeById(id);
    }

    @GetMapping(path = "/product/{id}") // ok
    public Product getProductById(@PathVariable Long id){ return this.service.getProductById(id);


    }
}
