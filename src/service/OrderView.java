package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Order;
import persistence.dao.OrderDao;

public class OrderView {
	
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private OrderDao orderDao = new OrderDao();
	List<Order> orders = new ArrayList<>();


	public void MethodFactory() throws SQLException {
		
		System.out.println("\n****************************************");
		System.out.println("Choose a method! Press number");
		do {
			System.out.println(
					"Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
					+ "\nFunction 3: FindOne \nFunction 4: Update "
					+ "\nFunction 5: Delete"
					+"\nFunction 6: Back");
			
			System.out.println("\n****************************************");
			functionControll = input.nextInt();
			
			switch (functionControll) {
			
			case 0:
				System.out.println("Exit");
				controllerBool = false;
				break;
			case 1:
				orderDao.create(Create());
					controllerBool = true;
						break;
			case 2:
				FindAll();
					controllerBool = true;
						break;
			case 3:
				Find();
					controllerBool = true;
						break;
			case 4:
				Update();
					controllerBool = true;
						break;
			case 5:
				Delete();
					controllerBool = true;
						break;
			case 6:
				JdbcConsoleUi jdbcConsole = new JdbcConsoleUi();
					jdbcConsole.Menu();
						controllerBool = false;
							break;
			}
			
		} while (controllerBool == true);
	}
	
	
	public Order Create() {
		
		int productId;
		int customerId;
		int stock;
		
		Order order = new Order();
		
		System.out.println("Creating new order, ProductId?");
		productId = input.nextInt();
		order.setProductId(productId);
		System.out.println("Customer id?");
		customerId = input.nextInt();
		order.setCustomerId(customerId);
		System.out.println("Stock?");
		stock = input.nextInt();
		order.setStock(stock);
		
		return order;
	}
	
	public void FindAll() throws SQLException {
		orders = orderDao.findAll();
		for(Order o : orders) {
			System.out.println("ID: " + o.getOrderId() + " customerID: " + o.getCustomerId() + " stock: " + o.getStock());
		}
		orders.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Order o = orderDao.find(x);
		System.out.println(o.getOrderId() + o.getCustomerId() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		int stock;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose order!");
		x = input.nextInt();
		Order o = orderDao.find(x);

		
		System.out.println("Updating stock, Stock?");
		stock = input.nextInt();
		o.setStock(stock);
		orderDao.update(o);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose complaint!");
		x = input.nextInt();
		Order o = orderDao.find(x);
		orderDao.delete(o);
	}
}
