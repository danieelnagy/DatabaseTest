package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Positions;
import persistence.ConnectionFactory;

public class PositionsDao implements DAO<Positions>{
	
	List<Positions> positions = new ArrayList<>();

	@Override
	public int create(Positions position) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into positions(position) values(?)");
		statement.setString(1, position.getPosition());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Positions> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from positions");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Positions position = new Positions();
			position.setPositionId(rs.getInt("position_id"));
			position.setPosition(rs.getString("position"));
			positions.add(position);
		}
		return positions;
	}

	@Override
	public Positions find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from positions where position_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Positions position = null;
        if(rs.next()) {
        	position = new Positions();
        	position.setPositionId(rs.getInt("position_id"));
        	position.setPosition(rs.getString("position"));
        }
        return position;
	}

	@Override
	public int update(Positions position) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update positions set position = ? where position_id = ?");
		statement.setString(1, position.getPosition());
		statement.setInt(2, position.getPositionId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Positions position) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from positions where position_id = ?");
		statement.setInt(1, position.getPositionId());
		System.out.println("Delted");
		return statement.executeUpdate();
	}

}
