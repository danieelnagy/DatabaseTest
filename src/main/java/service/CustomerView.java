package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Customer;
import persistence.dao.CustomerDao;

public class CustomerView {

	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private CustomerDao customerDao = new CustomerDao();
	List<Customer> customers = new ArrayList<>();

	public void MethodFactory() throws SQLException {
		
		System.out.println("\n****************************************");
		System.out.println("Choose a method! Press number");
		do {
			System.out.println(
					"Function 0: Back\nFunction 1: Create \nFunction 2: ListAll "
					+ "\nFunction 3: FindOne \nFunction 4: Update "
					+ "\nFunction 5: Delete \nFunction 6: Most paying customer");
			
			System.out.println("****************************************\n");
			functionControll = input.nextInt();
			
			switch (functionControll) {
			
			case 0:
				JdbcConsoleUi jdbcConsole = new JdbcConsoleUi();
				jdbcConsole.Menu();
				controllerBool = false;
				break;
			case 1:
				customerDao.create(create());
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
			case 6:
				getBestCustomer();
				controllerBool = true;
				break;
			}
			
		} while (controllerBool == true);
	}
	
	
	public Customer create() {
		
		String name;
		String adress;
		Customer customer = new Customer();
		
		System.out.println("Creating new customer, Name?");
		name = input.next();
		customer.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		customer.setAdress(adress);
		
		return customer;
	}
	
	public void findAll() throws SQLException {
		customers = customerDao.findAll();
		for(Customer c : customers) {
			System.out.println("ID: " + c.getCustomerId() + " Name: " + c.getName() + " Adress: " + c.getAdress());
		}
		customers.clear();
	}
	
	public void find() throws SQLException {
		
		int x = 0;
		findAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Customer c = customerDao.find(x);
		System.out.println("ID: " + c.getCustomerId() + " Name: " + c.getName() + " Adress: " + c.getAdress() + "\nfound");
	}

	
	public void update() throws SQLException {
		
		String name;
		String adress;
		String email;
		int x = 0;	
		findAll();
		System.out.println("Type id to choose customer!");
		x = input.nextInt();
		Customer c = customerDao.find(x);

		
		System.out.println("Updating customer, Name?");
		name = input.next();
		c.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		c.setAdress(adress);
		System.out.println("Email?");
		email = input.next();
		c.setEmail(email);
		customerDao.update(c);
	}
	
	public void delete() throws SQLException {
		
		int x = 0;	
		findAll();
		System.out.println("Type id to choose customer!");
		x = input.nextInt();
		Customer c = customerDao.find(x);
		customerDao.delete(c);
	}

	public void getBestCustomer() throws SQLException {

		customers = customerDao.getHighestMoneySpent();
		Customer apexCustomer = customers.get(0);
		System.out.println("ID: " + apexCustomer.getCustomerId() + " Name: " + apexCustomer.getName() + " Adress: "
				+ apexCustomer.getAdress() + " moneySpent: " + apexCustomer.getTotalSpentMoney());
	}

	
}
