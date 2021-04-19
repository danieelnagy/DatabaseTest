package application;

import java.sql.SQLException;
import java.util.Scanner;

public class DbMenu {
	
	
	/* TODO : JDBC DATES ORDER */
	
	private Scanner input = new Scanner(System.in);
	private Boolean controllerBool = true;
	private int functionControll = 0;
	private JdbcConsoleUi jdbcMenu;
	
	
	public void Menu() throws SQLException
	{
		
		System.out.println("Choose your database! Press number");
		do
		{
		System.out.println("Function 0: Exit\nFunction 1: JDBC \nFunction 2: Hibernate");
		functionControll = input.nextInt(); 
		switch (functionControll)
		{
		case 0: 
			System.out.println("Exit"); 
				controllerBool = false;
					break;
		case 1: 
			jdbcMenu = new JdbcConsoleUi(); 
				jdbcMenu.Menu();  
					controllerBool = true;
						break;
		case 2:
			controllerBool = true;
			break;
		}
		}while(controllerBool == true);
	}
}
