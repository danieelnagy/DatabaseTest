package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Complaint;
import entities.Order;
import persistence.ConnectionFactory;

public class OrderDao implements DAO<Order>{
	
	List<Order> orders = new ArrayList<>();

	@Override
	public int create(Order order) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into orders(product_id, customer_id, num_products, order_date, shipment_date, arrival_date) values(?,?,?,?,?,?)");
		statement.setInt(1, order.getProductId());
		statement.setInt(2, order.getCustomerId());
		statement.setInt(3, order.getStock());
		statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
		statement.setDate(5, new java.sql.Date(new java.util.Date().getTime() + 3));
		statement.setDate(6, new java.sql.Date(new java.util.Date().getTime() + 6));
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Order> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from orders");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Order order = new Order();
			order.setOrderId(rs.getInt("order_id"));
			order.setCustomerId(rs.getInt("customer_id"));
			order.setOrderDate(rs.getDate("order_date"));
			orders.add(order);
		}
		return orders;
	}

	@Override
	public Order find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from orders where order_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Order order = null;
        if(rs.next()) {
        	order = new Order();
        	order.setOrderId(rs.getInt("order_id"));
        	order.setCustomerId(rs.getInt("customer_id"));
        }
        return order;
	}

	@Override
	public int update(Order order) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update orders set num_products = ? where order_id = ?");
		statement.setInt(1, order.getStock());
		statement.setInt(2, order.getOrderId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Order order) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from orders where order_id = ?");
		statement.setInt(1, order.getOrderId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}

}
