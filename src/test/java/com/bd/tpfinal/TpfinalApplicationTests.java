package com.bd.tpfinal;

import com.bd.tpfinal.services.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TpfinalApplicationTests {

//	@Autowired
//	private DeliveryService service;

	@Test
	void contextLoads() {
	}
	@SpringBootTest
	class DeliveryApplicationTests {

		@Autowired
		private DeliveryService service;


		@Test
		void creationGetTest(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);

			assertNotNull(this.service.getClientInfo("clienteuno"));
			assertEquals(this.service.getClientInfo("clienteuno").getEmail(), "clienteuno@mail.com");
			assertNull(this.service.getClientInfo("clientedos"));

			assertNotNull(this.service.getDeliveryManInfo("dmuno"));
			assertEquals(this.service.getDeliveryManInfo("dmuno").getEmail(), "dmuno@mail.com");
			assertNull(this.service.getDeliveryManInfo("dmdos"));
		}

		@Test
		void editTest(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);

			client1.setName("Cliente Unooo");
			deliveryMan1.setEmail("dm1@mail.com");
			try{
				this.service.editClient(client1.getUsername(), client1);
				this.service.editDeliveryMan(deliveryMan1.getUsername(), deliveryMan1);
			} catch (Exception e){
				assertTrue(false);
			}

			assertEquals(this.service.getClientInfo("clienteuno").getName(), "Cliente Unooo");
			assertEquals(this.service.getDeliveryManInfo("dmuno").getEmail(), "dm1@mail.com");
		}

		@Test
		void desactiveTest(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);

			this.service.desactiveClient("clienteuno");
			this.service.desactiveDeliveryMan("dmuno");

			assertFalse(this.service.getClientInfo("clienteuno").isActive());
			assertFalse(this.service.getDeliveryManInfo("dmuno").isActive());
		}

		@Test
		void orderTest() {
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "Calle 1 n1", "Unos productos", 10,10, 100, client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertTrue(this.service.getOrderinfo(numberOrder).getNumber() == order.getNumber());

			boolean res1 = this.service.assignOrder(numberOrder);
			assertFalse(res1);

			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);

			boolean res2 = this.service.assignOrder(numberOrder);
			assertTrue(res2);

			assertTrue(this.service.getOrderinfo(numberOrder).getDeliveryMan() != null);
			assertEquals(this.service.getOrderinfo(numberOrder).getDeliveryMan().getUsername(), "dmuno");
			assertFalse(this.service.getOrderinfo(numberOrder).getDeliveryMan().isFree());
			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Assigned");
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 1);

			try{
				this.service.acceptOrder(numberOrder);
			}catch (Exception e) {
				assertTrue(false);
			}

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Sent");

			try{
				this.service.finishOrder(numberOrder);
			} catch (Exception e) {
				assertTrue(false);
			}

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Delivered");
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccessfulOrders(), 1);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getScore(), 1);
			assertEquals(this.service.getClientInfo("clienteuno").getScore(), 1);

		}

		@Test
		void cancel1Test(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "Calle 1 n1", "Unos productos", 10,10, 100, client1);
			order = this.service.newOrderPending(order);
			long numberOrder = order.getNumber();

			Order order1 = this.service.getOrderinfo(order.getNumber());

			assertEquals(order1.getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertTrue(this.service.getOrderinfo(numberOrder).getNumber() == numberOrder);

			try{
				this.service.cancelOrder(numberOrder);
			}catch (Exception e) {
				assertTrue(false);
			}

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Cancelled");
			assertEquals(this.service.getClientInfo("clienteuno").getScore(), -1);

		}

		@Test
		void cancel2Test(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "Calle 1 n1", "Unos productos", 10,10, 100, client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();

			assertEquals(order.getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertEquals(this.service.getOrderinfo(numberOrder).getNumber(), numberOrder);

			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);
			this.service.assignOrder(numberOrder);

			assertTrue(this.service.getOrderinfo(numberOrder).getDeliveryMan() != null);
			assertEquals(this.service.getOrderinfo(numberOrder).getDeliveryMan().getUsername(), "dmuno");
			assertFalse(this.service.getDeliveryManInfo("dmuno").isFree());
			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Assigned");
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 1);

			try{
				this.service.cancelOrder(numberOrder);
			}catch (Exception e) {
				assertTrue(false);
			}

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Cancelled");
			assertEquals(this.service.getClientInfo("clienteuno").getScore(), -2);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccessfulOrders(), 0);
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 0);

		}

		@Test
		void refuseTest(){
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", new Date(1990, 3, 3));
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "Calle 1 n1", "Unos productos", 10,10, 100, client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();


			assertEquals(order.getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertEquals(this.service.getOrderinfo(numberOrder).getNumber(), numberOrder);

			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", new Date(1995, 3, 6));
			this.service.newDeliveryMan(deliveryMan1);
			this.service.assignOrder(numberOrder);

			assertTrue(this.service.getOrderinfo(numberOrder).getDeliveryMan() != null);
			assertEquals(this.service.getOrderinfo(numberOrder).getDeliveryMan().getUsername(), "dmuno");
			assertFalse(this.service.getDeliveryManInfo("dmuno").isFree());
			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Assigned");
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 1);

			try{
				this.service.refuseOrder(numberOrder);
			}catch (Exception e) {
				assertTrue(false);
			}

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Cancelled");
			assertEquals(this.service.getDeliveryManInfo("dmuno").getScore(), -2);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccessfulOrders(), 0);
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 0);
		}

	@Test
	void prueba() {
		System.out.println("OK!");
	}

}
