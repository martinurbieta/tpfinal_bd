package com.bd.tpfinal.services;

import com.bd.tpfinal.model.*;

public interface DeliveryService {

    /*
    **   Interface que define los metodos que debe implementar un servicio
     */

    User CreateUser(User user);//sacar

    Client CreateClient(Client client);

    Address CreateAddress(Address address);

    DeliveryMan CreateDeliveryMan(DeliveryMan deliveryMan);

    Order CreateOrder(Order order);


}
