package com.bd.tpfinal.controllers;

import com.bd.tpfinal.model.Client;
import com.bd.tpfinal.model.DeliveryMan;
import com.bd.tpfinal.model.Order;
import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT ,RequestMethod.DELETE})
public class DeliveryController {

//    @Autowired
//    private DeliveryService service;

    @GetMapping("/test")
    public String test(){
        return "OK!";
    }


    @Autowired
    public DeliveryService service;

    @PostMapping(path = "/client")
    public Client newClient(@RequestBody Client client){
        return this.service.newClient(client);
    }

    @PutMapping(path = "/client/{username}")
    public Client editClient(@RequestBody Client client, @PathVariable String username) throws DeliveryException{
        return this.service.editClient(username, client);
    }

    @DeleteMapping(path = "/client/{username}")
    public void desactiveClient(@PathVariable String username){
        this.service.desactiveClient(username);
    }

    @GetMapping(path = "/client/{username}")
    public Client getClient(@PathVariable String username){
        return this.service.getClientInfo(username);
    }

    @GetMapping(path = "/client/{username}/orders")
    public List<Order> getClientOrders(@PathVariable String username){
        return this.service.getClientOrders(username);
    }

    @PostMapping(path = "/deliveryMan")
    public DeliveryMan newDeliveryMan(@RequestBody DeliveryMan deliveryMan){
        return this.service.newDeliveryMan(deliveryMan);
    }

    @PutMapping(path = "/deliveryMan/{username}")
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
}