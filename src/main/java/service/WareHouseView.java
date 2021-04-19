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
				warehouseDao.create(create());
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
	
	
	public WareHouse create() {
		
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
	
	public void findAll() throws SQLException {
		warehouses = warehouseDao.findAll();
		for(WareHouse w : warehouses) {
			System.out.println("ID: " + w.getWareHouseId() + " Name: " + w.getName() + " Adress: " + w.getAdress());
		}
		warehouses.clear();
	}
	
	public void find() throws SQLException {
		
		int x = 0;
		findAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		WareHouse w = warehouseDao.find(x);
		System.out.println("ID: " + w.getWareHouseId() + " Name: " + w.getName() + " Adress: " + w.getAdress());
		System.out.println("\nfound");
	}

	
	public void update() throws SQLException {
		
		String name;
		String adress;
		int x = 0;	
		findAll();
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
	
	public void delete() throws SQLException {
		
		int x = 0;	
		findAll();
		System.out.println("Type id to choose warehouse!");
		x = input.nextInt();
		WareHouse w = warehouseDao.find(x);
		warehouseDao.delete(w);
	}
	
}
