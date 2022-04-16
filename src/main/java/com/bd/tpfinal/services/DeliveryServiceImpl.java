package com.bd.tpfinal.services;

import  com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    /*@Autowired
    private EjemploRepository repository;*/

    @Autowired
    private UserRepository userRepository; //Sacar

    @Autowired
    private  ClientRepository clientRepository;

    @Autowired
    private  AddressRepository addressRepository;

    @Autowired
    private DeliveryManRepository deliveryManRepository;

    @Autowired
    private  OrderRepository orderRepository;

    @Override
    @Transactional
    public User CreateUser(User user)
    {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public Client CreateClient(Client client)
    {
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Address CreateAddress(Address address)
    {
        return this.addressRepository.save(address);
    }

    @Override
    @Transactional
    public Order CreateOrder(Order order)
    {
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public DeliveryMan CreateDeliveryMan(DeliveryMan deliveryMan){return this.deliveryManRepository.save(deliveryMan);}

    /*
    **    Implementacion de los metodos de la capa de servicio
     */
}
