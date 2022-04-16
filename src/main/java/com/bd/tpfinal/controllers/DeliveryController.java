package com.bd.tpfinal.controllers;

import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT ,RequestMethod.DELETE})
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @GetMapping("/test")
    public String test(){
        return "OK!";
    }

    @PostMapping(path = "/orderds")
    public Order NewOrder(@RequestBody Order order){return this.service.CreateOrder(order);}

    //@PostMapping (path = "/users")
    //public User NewUser(@RequestBody User user){return this.service.CreateUser(user);}

    @PostMapping (path = "/address")
    public Address NewAddress(@RequestBody Address address){return  this.service.CreateAddress(address);}

    @PostMapping(path = "/deliveryman")
    public DeliveryMan NewDeliveryMan(@RequestBody DeliveryMan deliveryMan){return  this.service.CreateDeliveryMan(deliveryMan);}

    @PostMapping (path = "/client")
    public Client NewClient(@RequestBody Client client){return this.service.CreateClient(client);}


    /*
    *       Controllador de la aplicacion, aqui se definen los endpoints
     */

}
