package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Customer;
import entities.Order;
import entities.Product;
import persistence.dao.CustomerDao;
import persistence.dao.OrderDao;
import persistence.dao.ProductDao;

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
					"Function 0: Back\nFunction 1: Create \nFunction 2: ListAll "
					+ "\nFunction 3: FindOne \nFunction 4: Update "
					+ "\nFunction 5: Delete");
			
			System.out.println("****************************************\n");
			functionControll = input.nextInt();
			
			switch (functionControll) {
			
			case 0:
				JdbcConsoleUi jdbcConsole = new JdbcConsoleUi();
				jdbcConsole.Menu();
				controllerBool = false;
				break;
			case 1:
				orderDao.create(create());
					controllerBool = true;
						break;
			case 2:
				findAll();
					controllerBool = true;
						break;
			case 3:
				find();
					controllerBool = true;
						break;
			case 4:
				update();
					controllerBool = true;
						break;
			case 5:
				delete();
					controllerBool = true;
						break;
			}
			
		} while (controllerBool == true);
	}
	
	
	public Order create() throws SQLException {
		
		int productId;
		int customerId;
		int stock;

		ProductsView productsView = new ProductsView();
		ProductDao productDao = new ProductDao();

		CustomerView customerView = new CustomerView();
		CustomerDao customerDao = new CustomerDao();

		Order order = new Order();

		/* Order hantering */
		System.out.println("Creating new order, ProductId?");
		productsView.findAll();
		productId = input.nextInt();
		order.setProductId(productId);
		System.out.println("Customer id?");
		customerId = input.nextInt();
		order.setCustomerId(customerId);
		System.out.println("Amount of item?");
		stock = input.nextInt();
		order.setStock(stock);

		/* Product hantering */
		Product product = productDao.find(productId);
		System.out.println("productStockBEFORE: " + "productName: " + product.getName() +  " Stock: " + product.getStock());
		product.setStock(product.getStock() - stock);
		productDao.update(product);
		System.out.println("productStockAFTER: " + "productName: " + product.getName() +  " Stock: " + product.getStock());

		/* Customer hantering */

		Customer customer = customerDao.find(customerId);
		System.out.println("CustomerSPENTMONEY BEFORE: " + customer.getTotalSpentMoney() + " Name: " + customer.getName());
		customer.setTotalSpentMoney(customer.getTotalSpentMoney() + (int)product.getPrice());
		customerDao.update(customer);
		System.out.println("CustomerSPENTMONEY AFTER: " + customer.getTotalSpentMoney() + " Name: " + customer.getName());

		
		return order;
	}
	
	public void findAll() throws SQLException {
		orders = orderDao.findAll();
		for(Order o : orders) {
			System.out.println("ID: " + o.getOrderId() + " customerID: " + o.getCustomerId() + " stock: " + o.getStock());
		}
		orders.clear();
	}
	
	public void find() throws SQLException {
		
		int x = 0;
		findAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Order o = orderDao.find(x);
		System.out.println(o.getOrderId() + o.getCustomerId() + "\nfound");	
	}

	
	public void update() throws SQLException {
		
		int stock;
		int x = 0;	
		findAll();
		System.out.println("Type id to choose order!");
		x = input.nextInt();
		Order o = orderDao.find(x);

		
		System.out.println("Updating stock, Stock?");
		stock = input.nextInt();
		o.setStock(stock);
		orderDao.update(o);
	}
	
	public void delete() throws SQLException {
		
		int x = 0;	
		findAll();
		System.out.println("Type id to choose complaint!");
		x = input.nextInt();
		Order o = orderDao.find(x);
		orderDao.delete(o);
	}
}
