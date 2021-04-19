package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Complaint;
import persistence.ConnectionFactory;

public class ComplaintDao implements DAO<Complaint>{
	
	List<Complaint> complaints = new ArrayList<>();

	@Override
	public int create(Complaint complaint) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into complaints(product_id, customer_id, employee_id, complaint_date, describe_complaint) values(?,?,?,?,?)");
		statement.setInt(1, complaint.getProduct_id());
		statement.setInt(2, complaint.getCustomer_id());
		statement.setInt(3, complaint.getEmployee_id());
		statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
		statement.setString(5, complaint.getDescribe());
		System.out.println("\nDone, with the the id: " + complaint.getComplaint_id() + ","
				+ " Description: " + complaint.getDescribe());
		return statement.executeUpdate();
	}

	@Override
	public List<Complaint> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from complaints");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Complaint complaint = new Complaint();
			complaint.setComplaint_id(rs.getInt("complaint_id"));
			complaint.setCustomer_id(rs.getInt("customer_id"));
			complaint.setDescribe(rs.getString("describe_complaint"));
			complaint.setDate(rs.getDate("complaint_date"));
			complaints.add(complaint);
		}
		return complaints;
	}

	@Override
	public Complaint find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from complaints where complaint_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Complaint complaint = null;
        if(rs.next()) {
        	complaint = new Complaint();
        	complaint.setComplaint_id(rs.getInt("complaint_id"));
        	complaint.setCustomer_id(rs.getInt("customer_id"));
        	complaint.setDescribe(rs.getString("describe_complaint"));
        	complaint.setDate(rs.getDate("complaint_date"));

        }
        return complaint;
	}

	@Override
	public int update(Complaint complaint) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update complaints set describe_complaint = ? where complaint_id = ?");
		statement.setString(1, complaint.getDescribe());
		statement.setInt(2, complaint.getComplaint_id());
		System.out.println("Updated with the new description: " + complaint.getDescribe() + "\n");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Complaint complaint) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from complaints where complaint_id = ?");
		statement.setInt(1, complaint.getComplaint_id());
		System.out.println("Deleted\n" );
		return statement.executeUpdate();
	}

}
