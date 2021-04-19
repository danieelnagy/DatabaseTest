package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.JdbcConsoleUi;
import entities.Office;
import persistence.dao.OfficeDao;

public class OfficeView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private OfficeDao officeDao = new OfficeDao();
	List<Office> offices = new ArrayList<>();

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
				officeDao.create(Create());
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
	
	
	public Office Create() {
		
		String name;
		String adress;
		Office o = new Office();
		
		System.out.println("Creating a new office, Name?");
		name = input.next();
		o.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		o.setAdress(adress);
		
		return o;
	}
	
	public void FindAll() throws SQLException {
		offices = officeDao.findAll();
		for(Office o : offices) {
			System.out.println("ID: " + o.getOfficeId() + " " + o.getName() + " " + o.getAdress());
		}
		offices.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Office o = officeDao.find(x);
		System.out.println(o.getName() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String name;
		String adress;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose office!");
		x = input.nextInt();
		Office o = officeDao.find(x);
		
		System.out.println("Updating office, Name?");
		name = input.next();
		o.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		o.setAdress(adress);
		officeDao.update(o);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose office!");
		x = input.nextInt();
		Office o = officeDao.find(x);
		officeDao.delete(o);
	}
}
