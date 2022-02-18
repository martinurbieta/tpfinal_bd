package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DeliveryServiceImpl implements DeliveryService{

    @Autowired
    private EjemploRepository repository;

    @Autowired
    private DeliveryManRepository deliveryManRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;


    @Override
    @Transactional
    public Client newClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client editClient(String username, Client client) throws DeliveryException {
        Client realClient = this.getClientInfo(username);
        realClient = (Client) this.editUser(realClient, client);
        return this.clientRepository.save(realClient);
    }

    @Override
    @Transactional
    public void desactiveClient(String username) {
        Client realClient = this.getClientInfo(username);
        if (realClient != null && realClient.isActive()) {
            realClient.setActive(false);
        }
        this.clientRepository.save(realClient);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientInfo(String username) {
//        return DeliveryRoot.getClientList().stream().filter(client1 ->
//                username.equals(client1.getUsername()) && client1.isActive()).findAny().orElse(null);
        return this.clientRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getClientOrders(String username) {
        Client client = this.getClientInfo(username);
        return (client != null) ? client.getOrders() : null;
    }

    @Override
    @Transactional
    public DeliveryMan newDeliveryMan(DeliveryMan deliveryMan) {
        return this.deliveryManRepository.save(deliveryMan);
    }

    @Override
    @Transactional
    public DeliveryMan editDeliveryMan(String username, DeliveryMan deliveryMan) throws DeliveryException {
        DeliveryMan realDeliveryMan = this.getDeliveryManInfo(username);
        realDeliveryMan = (DeliveryMan) this.editUser(realDeliveryMan, deliveryMan);
        return this.deliveryManRepository.save(realDeliveryMan);
    }

    private User editUser(User realUser, User user) throws DeliveryException {
        if (user != null) {
            realUser.setName(user.getName());
            realUser.setEmail(user.getEmail());
            realUser.setDateOfBirth(user.getDateOfBirth());
            realUser.setPassword(user.getPassword());
        } else {
            throw new DeliveryException("The client with username does not exist");
        }
        return realUser;
    }

    @Override
    @Transactional
    public void desactiveDeliveryMan(String username) {
        DeliveryMan deliveryMan = this.getDeliveryManInfo(username);
        if (deliveryMan != null && deliveryMan.isActive()) {
            deliveryMan.setActive(false);
        }
        this.deliveryManRepository.save(deliveryMan);
    }

    @Override
    @Transactional(readOnly = true)
    public DeliveryMan getDeliveryManInfo(String username) {
//        return DeliveryRoot.getDeliveryManList().stream().filter(dm ->
//                username.equals(dm.getUsername()) && dm.isActive()).findAny().orElse(null);
        return this.deliveryManRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public Order newOrderPending(Order order) {
        order.getClient().addOrder(order);
        this.orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderinfo(long number) {
//        return DeliveryRoot.getOrderList().stream().filter(order ->
//                order.getNumber() == number).findAny().orElse(null);
        Order order = this.orderRepository.findById(number).orElse(null);
  //      if (order != null) order.setStatusByName();
        return order;
    }

    @Override
    @Transactional
    public boolean assignOrder(long number) {
//        DeliveryMan deliveryMan = DeliveryRoot.getDeliveryManList().stream().filter(dm ->
//                dm.isFree() && dm.isActive()).findAny().orElse(null);
        DeliveryMan deliveryMan = this.deliveryManRepository.findByFreeTrueAndActiveTrue().stream().findAny().orElse(null);
        if (deliveryMan != null) {
            try {
                Order order = this.getOrderinfo(number);
                deliveryMan.getActualOrders().size(); // Inicializar lista LAZY
                //order.assign(deliveryMan);
                order.getOrderStatus().assign(deliveryMan);
                this.orderRepository.save(order);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<Order> getAssignedOrders(String username) {
        DeliveryMan dm = this.getDeliveryManInfo(username);
        dm.getActualOrders().size(); // Inicializar lista LAZY
        return dm.getActualOrders();
    }

    @Override
    @Transactional
    public void deliverOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        order.getDeliveryMan().getActualOrders().size(); // Inicializar lista LAZY
        //order.deliver();
        order.getOrderStatus().deliver();
        this.orderRepository.save(order); // Tambien guardamos el DeliveryMan y el Client, debido a las oper en cadena
    }

    @Override
    @Transactional
    public void refuseOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        //order.refuse();
        order.getOrderStatus().refuse();
        this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        //order.cancel();
        order.getOrderStatus().cancel();
        this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public void finishOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        //order.finish();
        order.getOrderStatus().cancel();
        this.orderRepository.save(order);
    }
}
