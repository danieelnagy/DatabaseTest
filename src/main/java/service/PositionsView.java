package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.JdbcConsoleUi;
import entities.Positions;
import persistence.dao.PositionsDao;

public class PositionsView {
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private PositionsDao positionDao = new PositionsDao();
	List<Positions> positions = new ArrayList<>();

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
				positionDao.create(create());
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
	
	
	public Positions create() throws SQLException {
		
		String positionName;
		Positions p = new Positions();
		
		System.out.println("Creating new position, position (only 4 chr)?");
		positionName = input.next();
		p.setPosition(positionName);
		
		return p;
	}
	
	public void findAll() throws SQLException {
		positions = positionDao.findAll();
		for(Positions p : positions) {
			System.out.println("ID: " + p.getPositionId() + " Position: " + p.getPosition());
		}
		positions.clear();
	}
	
	public void find() throws SQLException {
		
		int x = 0;
		findAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		System.out.println("ID: " + p.getPositionId() + " Position: " + p.getPosition());
		System.out.println("\nfound");
	}

	
	public void update() throws SQLException {
		
		String positionName;
		int x = 0;	
		findAll();
		System.out.println("Type id to choose position!");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		
		System.out.println("Updating position, positionName?");
		positionName = input.next();
		p.setPosition(positionName);
		positionDao.update(p);
	}
	
	public void delete() throws SQLException {
		
		int x = 0;	
		findAll();
		System.out.println("Type id to choose position!");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		positionDao.delete(p);
	}
	
}
