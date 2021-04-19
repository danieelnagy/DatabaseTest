package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Office;
import persistence.ConnectionFactory;

public class OfficeDao implements DAO<Office>{
	
	List<Office> offices = new ArrayList<>();

	@Override
	public int create(Office office) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("insert into offices(name, adress) values(?,?)");
		statement.setString(1, office.getName());
		statement.setString(2, office.getAdress());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Office> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from offices");
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Office office = new Office();
			office.setOfficeId(rs.getInt("office_id"));
			office.setName(rs.getString("name"));
			office.setAdress(rs.getNString("adress"));
			offices.add(office);
		}
		return offices;
	}

	@Override
	public Office find(Object id) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from offices where office_id = ?");
		statement.setInt(1, (int) id);
		ResultSet rs = statement.executeQuery();
		Office office = null;
		if (rs.next()) {
			office = new Office();
			office.setOfficeId(rs.getInt("office_id"));
			office.setName(rs.getString("name"));
			office.setAdress("adress");
		}
		return office;
	}

	@Override
	public int update(Office office) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("update offices set name = ?, adress = ? where office_id = ?");
		statement.setString(1, office.getName());
		statement.setString(2, office.getAdress());
		statement.setInt(3, office.getOfficeId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Office office) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from offices where office_id = ?");
		statement.setInt(1, office.getOfficeId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}
}
