package com.bd.tpfinal;

import com.bd.tpfinal.services.DeliveryService;
import com.bd.tpfinal.model.*;
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
			Date dateClient1 = new Date() ;
			Date dateDeliveryMan1 = new Date() ;

			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
			this.service.newDeliveryMan(deliveryMan1);

			assertNotNull(this.service.getClientInfo("clienteuno"));
			assertEquals(this.service.getClientInfo("clienteuno").getEmail(), "clienteuno@mail.com");
			assertEquals(this.service.getClientInfo("clienteuno").getDateOfBirth(), dateClient1);
			assertNull(this.service.getClientInfo("clientedos"));

			assertNotNull(this.service.getDeliveryManInfo("dmuno"));
			assertEquals(this.service.getDeliveryManInfo("dmuno").getEmail(), "dmuno@mail.com");
			assertEquals(this.service.getDeliveryManInfo("dmuno").getDateOfBirth(), dateDeliveryMan1);
			assertNull(this.service.getDeliveryManInfo("dmdos"));
		}

		@Test
		void editTest(){
			Date dateClient1 = new Date() ;
			Date dateDeliveryMan1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
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
			Date dateClient1 = new Date() ;
			Date dateDeliveryMan1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
			this.service.newDeliveryMan(deliveryMan1);

			this.service.desactiveClient("clienteuno");
			this.service.desactiveDeliveryMan("dmuno");

			assertFalse(this.service.getClientInfo("clienteuno").isActive());
			assertFalse(this.service.getDeliveryManInfo("dmuno").isActive());
		}

		@Test
		void orderTest() {
			Date dateClient1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "No comments please", 2000,client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();

			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertTrue(this.service.getOrderinfo(numberOrder).getNumber() == order.getNumber());

			boolean res1 = this.service.assignOrder(numberOrder);
			assertFalse(res1);

			Date dateDeliveryMan1 = new Date() ;
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
			this.service.newDeliveryMan(deliveryMan1);

			boolean res2 = this.service.assignOrder(numberOrder);
			assertTrue(res2);

			assertTrue(this.service.getOrderinfo(numberOrder).getDeliveryMan() != null);
			assertEquals(this.service.getOrderinfo(numberOrder).getDeliveryMan().getUsername(), "dmuno");
			assertFalse(this.service.getOrderinfo(numberOrder).getDeliveryMan().isFree());
			assertEquals(this.service.getOrderinfo(numberOrder).getOrderStatus().getClass().getName(), "com.bd.delivery.model.Assigned");
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 1);

			try{
				this.service.deliverOrder(numberOrder);
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
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccess(), 1);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getScore(), 1);
			assertEquals(this.service.getClientInfo("clienteuno").getScore(), 1);

		}

		@Test
		void cancel1Test(){
			Date dateClient1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "No comments please", 2000,client1);
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
			Date dateClient1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "No comments please", 2000,client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();

			assertEquals(order.getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertEquals(this.service.getOrderinfo(numberOrder).getNumber(), numberOrder);

			Date dateDeliveryMan1 = new Date() ;
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
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
			assertEquals(this.service.getClientInfo("clienteuno").getScore(), -1);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccess(), 0);
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 0);

		}

		@Test
		void refuseTest(){
			Date dateClient1 = new Date() ;
			Client client1 = new Client("Cliente Uno", "clienteuno@mail.com", "clienteuno", "1234", dateClient1);
			this.service.newClient(client1);
			Order order = new Order(Calendar.getInstance().getTime(), "No comments please", 2000,client1);
			this.service.newOrderPending(order);
			long numberOrder = order.getNumber();


			assertEquals(order.getOrderStatus().getClass().getName(), "com.bd.delivery.model.Pending");
			assertEquals(this.service.getOrderinfo(numberOrder).getNumber(), numberOrder);

			Date dateDeliveryMan1 = new Date() ;
			DeliveryMan deliveryMan1 = new DeliveryMan("Dm Uno", "dmuno@mail.com", "dmuno", "1234", dateDeliveryMan1);
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
			assertEquals(this.service.getDeliveryManInfo("dmuno").getScore(), -1);
			assertEquals(this.service.getDeliveryManInfo("dmuno").getNumberOfSuccess(), 0);
			assertEquals(this.service.getAssignedOrders("dmuno").size(), 0);
		}

	@Test
	void prueba() {
		System.out.println("OK!");
	}
	}
}
