package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.WareHouse;
import persistence.ConnectionFactory;

public class WareHouseDao implements DAO<WareHouse>{
	
	List<WareHouse> warehouses = new ArrayList<>();

	@Override
	public int create(WareHouse warehouse) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into warehouses(name, adress) values(?,?)");
		statement.setString(1, warehouse.getName());
		statement.setString(2, warehouse.getAdress());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<WareHouse> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from warehouses");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			WareHouse warehouse = new WareHouse();
			warehouse.setWareHouseId(rs.getInt("warehouse_id"));
			warehouse.setName(rs.getString("name"));
			warehouse.setAdress(rs.getNString("adress"));
			warehouses.add(warehouse);
		}
		return warehouses;
	}

	@Override
	public WareHouse find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from warehouses where warehouse_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        WareHouse warehouse = null;
        if(rs.next()) {
        	warehouse = new WareHouse();
        	warehouse.setWareHouseId(rs.getInt("warehouse_id"));
        	warehouse.setName(rs.getString("name"));
        	warehouse.setAdress("adress");
        }
        return warehouse;
	}

	@Override
	public int update(WareHouse warehouse) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update warehouses set name = ?, adress = ? where warehouse_id = ?");
		statement.setString(1, warehouse.getName());
		statement.setString(2, warehouse.getAdress());
		statement.setInt(3, warehouse.getWareHouseId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(WareHouse warehouse) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from warehouses where warehouse_id = ?");
		statement.setInt(1, warehouse.getWareHouseId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}

}
