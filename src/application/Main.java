package application;

import java.sql.SQLException;

import entities.Customer;
import persistence.SessionHibernate;

public class Main {

	public static void main(String[] args) throws SQLException {
		DbMenu menu = new DbMenu();
		//menu.Menu();

		//TODOOOOO DATES COMPLAINTS ORDERS
		
		SessionHibernate sh = new SessionHibernate();
		sh.setup();
		
		Customer c = new Customer(4, "ASD", "ASD", "ASD", "ASD", "ASDDD", 15);
		
		
		sh.create(c);
		
		sh.Exit();
		
		
	}

}
