package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Product;
import persistence.ConnectionFactory;

public class ProductDao implements DAO<Product>{

	List<Product> products = new ArrayList<>();

	@Override
	public int create(Product product) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into products(name, price, stock, warehouse_id, position_id) values(?,?,?,?,?)");
		statement.setString(1, product.getName());
		statement.setDouble(2, product.getPrice());
		statement.setInt(3, product.getStock());
		statement.setInt(4, product.getWareHouseId());
		statement.setInt(5, product.getPositionId());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Product> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from products");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setProductId(rs.getInt("product_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			products.add(product);
		}
		return products;
	}

	@Override
	public Product find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from products where product_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Product product = null;
        if(rs.next()) {
        	product = new Product();
        	product.setProductId(rs.getInt("product_id"));
        	product.setName(rs.getString("name"));
        	product.setPrice(rs.getDouble("price"));
        }
        return product;
	}

	@Override
	public int update(Product product) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update products set name = ?, stock = ?, price = ? where product_id = ?");
		statement.setString(1, product.getName());
		statement.setInt(2, product.getStock());
		statement.setDouble(3, product.getPrice());
		statement.setInt(4, product.getProductId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Product product) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from products where product_id = ?");
		statement.setInt(1, product.getProductId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}
}
