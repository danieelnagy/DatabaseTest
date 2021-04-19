package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.JdbcConsoleUi;
import entities.Employee;
import persistence.dao.EmployeeDao;



public class EmployeeView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private EmployeeDao employeeDao = new EmployeeDao();
	List<Employee> employees = new ArrayList<>();

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
				employeeDao.create(Create());
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
	
	
	public Employee Create() throws SQLException {
		
		String name;
		String adress;
		int officeId;
		Employee e = new Employee();
		
		System.out.println("Creating new employee, Name?");
		name = input.next();
		e.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		e.setAdress(adress);
		System.out.println("Office? (1-2 är useable)");
		officeId = input.nextInt();
		e.setOfficeId(officeId);
		
		return e;
	}
	
	public void FindAll() throws SQLException {
		employees = employeeDao.findAll();
		for(Employee e : employees) {
			System.out.println("ID: " + e.getEmployeeId() + " " + e.getName() + " " + e.getAdress());
		}
		employees.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Employee e = employeeDao.find(x);
		System.out.println(e.getName() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String name;
		String adress;
		int officeId;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose employee!");
		x = input.nextInt();
		Employee e = employeeDao.find(x);
		
		System.out.println("Updating employee, Name?");
		name = input.next();
		e.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		e.setAdress(adress);
		System.out.println("Office? (1-2 är useable)");
		officeId = input.nextInt();
		e.setOfficeId(officeId);
		employeeDao.update(e);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose employee!");
		x = input.nextInt();
		Employee e = employeeDao.find(x);
		employeeDao.delete(e);
	}
	
}

