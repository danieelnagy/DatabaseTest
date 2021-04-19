package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.JdbcConsoleUi;
import entities.Complaint;
import persistence.dao.ComplaintDao;

public class ComplaintsView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private ComplaintDao complaintDao = new ComplaintDao();
	List<Complaint> complaints = new ArrayList<>();


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
				complaintDao.create(Create());
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
	
	
	public Complaint Create() {
		
		String describe;
		int productId;
		int customerId;
		int employeeId;
		
		Complaint complaint = new Complaint();
		
		System.out.println("Creating new complaint, ProductId?");
		productId = input.nextInt();
		complaint.setProduct_id(productId);
		System.out.println("Your(customer) id?");
		customerId = input.nextInt();
		complaint.setCustomer_id(customerId);
		System.out.println("Employee?");
		employeeId = input.nextInt();
		complaint.setEmployee_id(employeeId);
		System.out.println("Describe?");
		describe = input.next();
		complaint.setDescribe(describe);
		
		return complaint;
	}
	
	public void FindAll() throws SQLException {
		complaints = complaintDao.findAll();
		for(Complaint c : complaints) {
			System.out.println("ID: " + c.getComplaint_id() + " " + c.getCustomer_id() + " " + c.getDate());
		}
		complaints.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Complaint c = complaintDao.find(x);
		System.out.println(c.getComplaint_id() + c.getCustomer_id() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String describe;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose complaint!");
		x = input.nextInt();
		Complaint c = complaintDao.find(x);

		
		System.out.println("Updating description, Name?");
		describe = input.next();
		c.setDescribe(describe);
		complaintDao.update(c);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose complaint!");
		x = input.nextInt();
		Complaint c = complaintDao.find(x);
		complaintDao.delete(c);
	}
}
