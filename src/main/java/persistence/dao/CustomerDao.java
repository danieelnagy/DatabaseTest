package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Customer;
import persistence.ConnectionFactory;

public class CustomerDao implements DAO<Customer> {
	
	List<Customer> customers = new ArrayList<>();

	@Override
	public int create(Customer customer) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into customers(name, adress) values(?,?)");
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getAdress());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Customer> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from customers");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setName(rs.getString("name"));
			customer.setAdress(rs.getNString("adress"));
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public Customer find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from customers where customer_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Customer customer = null;
        if(rs.next()) {
            customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setAdress("adress");
        }
        return customer;
	}

	@Override
	public int update(Customer customer) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update customers set name = ?, adress = ?, email = ? where customer_id = ?");
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getAdress());
		statement.setString(3, customer.getEmail());
		statement.setInt(4, customer.getCustomerId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Customer customer) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from customers where customer_id = ?");
		statement.setInt(1, customer.getCustomerId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}

	public List<Customer> getHighestMoneySpent() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from customers order by totalspentmoney desc");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setName(rs.getString("name"));
			customer.setAdress(rs.getNString("adress"));
			customer.setTotalSpentMoney(rs.getInt("totalspentmoney"));
			customers.add(customer);
		}
		return customers;
	}

}
