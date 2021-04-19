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
					"Function 0: Exit\nFunction 1: Create \nFunction 2: ListAll "
					+ "\nFunction 3: FindOne \nFunction 4: Update "
					+ "\nFunction 5: Delete \nFunction 6 NOT USEABLEq: BestCustomer"
					+"\nFunction 7: Back");
			
			System.out.println("\n****************************************");
			functionControll = input.nextInt();
			
			switch (functionControll) {
			
			case 0:
				System.out.println("Exit");
				controllerBool = false;
				break;
			case 1:
				customerDao.create(Create());
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
				controllerBool = true;
				break;
			case 7:
				JdbcConsoleUi jdbcConsole = new JdbcConsoleUi();
					jdbcConsole.Menu();
						controllerBool = false;
							break;
			}
			
		} while (controllerBool == true);
	}
	
	
	public Customer Create() {
		
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
	
	public void FindAll() throws SQLException {
		customers = customerDao.findAll();
		for(Customer c : customers) {
			System.out.println("ID: " + c.getCustomerId() + " " + c.getName() + " " + c.getAdress());
		}
		customers.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Customer c = customerDao.find(x);
		System.out.println(c.getName() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String name;
		String adress;
		String email;
		int x = 0;	
		FindAll();
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
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose customer!");
		x = input.nextInt();
		Customer c = customerDao.find(x);
		customerDao.delete(c);
	}
	
}
