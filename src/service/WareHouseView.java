package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.JdbcConsoleUi;
import entities.WareHouse;
import persistence.dao.WareHouseDao;

public class WareHouseView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private WareHouseDao warehouseDao = new WareHouseDao();
	List<WareHouse> warehouses = new ArrayList<>();

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
				warehouseDao.create(Create());
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
	
	
	public WareHouse Create() {
		
		String name;
		String adress;
		WareHouse warehouse = new WareHouse();
		
		System.out.println("Creating new warehouse, Name?");
		name = input.next();
		warehouse.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		warehouse.setAdress(adress);
		
		return warehouse;
	}
	
	public void FindAll() throws SQLException {
		warehouses = warehouseDao.findAll();
		for(WareHouse w : warehouses) {
			System.out.println("ID: " + w.getWareHouseId() + " " + w.getName() + " " + w.getAdress());
		}
		warehouses.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		WareHouse w = warehouseDao.find(x);
		System.out.println(w.getName() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String name;
		String adress;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose warehouse!");
		x = input.nextInt();
		WareHouse w = warehouseDao.find(x);

		
		System.out.println("Updating warehouse, Name?");
		name = input.next();
		w.setName(name);
		System.out.println("Adress?");
		adress = input.next();
		w.setAdress(adress);
		warehouseDao.update(w);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose warehouse!");
		x = input.nextInt();
		WareHouse w = warehouseDao.find(x);
		warehouseDao.delete(w);
	}
	
}
