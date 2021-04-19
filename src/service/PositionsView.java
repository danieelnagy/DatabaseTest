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
				positionDao.create(Create());
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
	
	
	public Positions Create() throws SQLException {
		
		String positionName;
		Positions p = new Positions();
		
		System.out.println("Creating new position, position (only 4 chr)?");
		positionName = input.next();
		p.setPosition(positionName);
		
		return p;
	}
	
	public void FindAll() throws SQLException {
		positions = positionDao.findAll();
		for(Positions p : positions) {
			System.out.println("ID: " + p.getPositionId() + " " + p.getPosition());
		}
		positions.clear();
	}
	
	public void Find() throws SQLException {
		
		int x = 0;
		FindAll();
		System.out.println("Enter the id number(felhantering för nonexist id ej gjort)");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		System.out.println(p.getPosition() + "\nfound");	
	}

	
	public void Update() throws SQLException {
		
		String positionName;
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose position!");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		
		System.out.println("Updating position, positionName?");
		positionName = input.next();
		p.setPosition(positionName);
		positionDao.update(p);
	}
	
	public void Delete() throws SQLException {
		
		int x = 0;	
		FindAll();
		System.out.println("Type id to choose position!");
		x = input.nextInt();
		Positions p = positionDao.find(x);
		positionDao.delete(p);
	}
	
}
