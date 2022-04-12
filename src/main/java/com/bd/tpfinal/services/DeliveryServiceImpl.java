package com.bd.tpfinal.services;
import com.bd.tpfinal.model.*;
import com.bd.tpfinal.repositories.*;
import com.bd.tpfinal.utils.DeliveryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService{

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
    private SupplierTypeRepository supplierTypeRepository;

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
        Order order = this.orderRepository.findById(number).orElse(null);
        return order;
    }

    @Override
    @Transactional
    public boolean assignOrder(long number) {
        DeliveryMan deliveryMan = this.deliveryManRepository.findByFreeTrueAndActiveTrue().stream().findAny().orElse(null);
        if (deliveryMan != null) {
            try {
                Order order = this.getOrderinfo(number);
                deliveryMan.getActualOrders().size(); // Inicializar lista LAZY
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
        order.getOrderStatus().deliver();
        this.orderRepository.save(order); // Tambien guardamos el DeliveryMan y el Client, debido a las oper en cadena
    }

    @Override
    @Transactional
    public void refuseOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        order.getOrderStatus().refuse();
        this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        order.getOrderStatus().cancel();
        this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public void finishOrder(long number) throws DeliveryException {
        Order order = this.getOrderinfo(number);
        order.getOrderStatus().finish();
        this.orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Address getAddress(int id_address) {
        return this.addressRepository.findById(id_address).orElse(null);
    }

    @Override
    @Transactional
    public Address createAddress(Address newAddress) {
        return this.addressRepository.save(newAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierType getSupplierType(Long id_supplier_type) {
        return this.supplierTypeRepository.findById(id_supplier_type).orElse(null);
    }

    @Override
    @Transactional
    public SupplierType createSupplierType(SupplierType newSupplierType) {
        return this.supplierTypeRepository.save(newSupplierType);
    }
    @Override
    @Transactional(readOnly = true)
    public Supplier getSupplier(Long id_supplier) {
        return this.supplierRepository.findById(id_supplier).orElse(null);
    }

    @Override
    @Transactional
    public Supplier createSupplier(Supplier newSupplier) {
        return this.supplierRepository.save(newSupplier);
    }



    @Override
    @Transactional
    public Object deleteItem(Item item) {
        try {
            this.itemRepository.delete(item);
        } catch (Exception e){
            return e;
        }
        return true;
    }

    @Override
    @Transactional
    public List<Item> getItemsByOrderID(Long id_order) {
        Optional<Order> order = this.orderRepository.findById(id_order);
        List<Item> items = order.isPresent() ? order.get().getItems() : null;
        return items;
}
