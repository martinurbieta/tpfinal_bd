package com.bd.tpfinal.controller;

import com.bd.tpfinal.model.*;
import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping(path = "/client/{username}/orders")
    public List<Order> getClientOrders(@PathVariable String username){
        return this.service.getClientOrders(username);
    }

    @PostMapping(path = "/deliveryMan") //ok
    public DeliveryMan newDeliveryMan(@RequestBody DeliveryMan deliveryMan){
        return this.service.newDeliveryMan(deliveryMan);
    }

    @PutMapping(path = "/deliveryMan/{username}")  // ok
    public DeliveryMan editDeliveryMan(@PathVariable String username, @RequestBody DeliveryMan deliveryMan) throws DeliveryException{
        return this.service.editDeliveryMan(username, deliveryMan);
    }

    @DeleteMapping(path = "/deliveryMan/{username}")
    public void desactiveDeliveryMan(@PathVariable String username){
        this.service.desactiveDeliveryMan(username);
    }

    @GetMapping(path = "/deliveryMan/{username}")
    public DeliveryMan getDeliveryMan(@PathVariable String username){
        return this.service.getDeliveryManInfo(username);
    }

    @PostMapping(path = "/order")
    public Order newOrder(@RequestBody Order order){
        return this.service.newOrderPending(order);
    }

    @GetMapping(path = "/order/{number}")
    public Order getOrder(@PathVariable long number){
        return this.service.getOrderinfo(number);
    }

    @GetMapping(path = "/deliveryMan/{username}/orders")
    public List<Order> getAssignedOrders(@PathVariable String username){
        return this.service.getAssignedOrders(username);
    }

    @PutMapping(path = "/order/{number}/assign")
    public boolean assignOrder(@PathVariable long number){
        return this.service.assignOrder(number);
    }

//    @PutMapping(path = "/order/{number}/accept")
//    public void acceptOrder(long number) throws DeliveryException {
//        this.service.acceptOrder(number);
//    }

    @PutMapping(path = "/order/{number}/refuse")
    public void refuseOrder(long number) throws DeliveryException {
        this.service.refuseOrder(number);
    }

    @PutMapping(path = "/order/{number}/cancel")
    public void cancelOrder(long number) throws DeliveryException{
        this.service.cancelOrder(number);
    }

    @PutMapping(path = "/order/{number}/finish")
    public void finishOrder(long number) throws DeliveryException {
        this.service.finishOrder(number);
    }

    @GetMapping(path = "/address/{id_address}")
    public Address getAddress(@PathVariable int id_address){
        return this.service.getAddress(id_address);
    }

    @PostMapping(path = "/address")
    public Address createAddress(@RequestBody Address newAddress){
        return this.service.createAddress(newAddress);
    }

    @GetMapping(path = "/supplierType/{id_supplier_type}")
    public SupplierType getSupplierType(@PathVariable long id_supplier_type){
        return this.service.getSupplierType(id_supplier_type);
    }

    @PostMapping(path = "/supplierType")
    public SupplierType createSupplierType(@RequestBody SupplierType newsSupplierType){
        return this.service.createSupplierType(newsSupplierType);
    }

    @GetMapping(path = "/supplier/{id_supplier}")
    public Supplier getSupplier(@PathVariable long id_supplier){
        return this.service.getSupplier(id_supplier);
    }

    @PostMapping(path = "/supplier")
    public Supplier createSupplier(@RequestBody Supplier newsSupplier){
        return this.service.createSupplier(newsSupplier);
    }

    @PostMapping(path = "/item")
    public Item createItem(@RequestBody Item newItem){
        return this.service.createItem(newItem);
    }

    @GetMapping(path = "/order/{items}")
    public List<Item> getItemsByOrderID(@PathVariable long number){
        return this.service.getItemsByOrderID(number);
    }
    @DeleteMapping(path = "/item/{idItem}")
    public Object deleteItem(@PathVariable int idItem){
        return this.service.deleteItem(this.service.findItemWithID(idItem);
    }
}