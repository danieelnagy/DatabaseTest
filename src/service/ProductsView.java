package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Product;
import persistence.dao.ProductDao;

public class ProductsView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private ProductDao productDao = new ProductDao();
	List<Product> products = new ArrayList<>();

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
				productDao.create(Create());
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
	
	
	public Product Create() {
		
		String name;
		double price;
		int stock;
		int warehouseId;
		int positionId;
		Product product = new Product();
		
		System.out.println("Creating new product, Name?");
		name = input.next();
		product.setName(name);
		System.out.println("Price?");
		price = input.nextDouble();
		product.setPrice(price);
		System.out.println("Stock?");
		stock = input.nextInt();
		product.setStock(stock);
		System.out.println("Warehouseid?");
		warehouseId = input.nextInt();
		product.setWareHouseId(warehouseId);
		System.out.println("Positionid?");
		positionId = input.nextInt();
		product.setPositionId(positionId);
		
		return product;
	}
	
	public void FindAll() throws SQLException {
		products = productDao.findAll();
		for(Product p : products) {
			System.out.println("ID: " + p.getProductId() + " " + p.getName() + " " + p.getPrice());
		}
		products.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Product p = productDao.find(x);
		System.out.println(p.getName() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String name;
		double price;
		int stock;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose product!");
		x = input.nextInt();
		Product p = productDao.find(x);

		
		System.out.println("Updating product, Name?");
		name = input.next();
		p.setName(name);
		System.out.println("Price?");
		price = input.nextDouble();
		p.setPrice(price);
		System.out.println("Stock?");
		stock = input.nextInt();
		p.setStock(stock);
		productDao.update(p);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose product!");
		x = input.nextInt();
		Product p = productDao.find(x);
		productDao.delete(p);
	}
}
