package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Employee;
import persistence.ConnectionFactory;

public class EmployeeDao implements DAO<Employee>{

	List<Employee> employees = new ArrayList<>();

	@Override
	public int create(Employee employee) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("insert into employees(name, adress, office_id) values(?,?,?)");
		statement.setString(1, employee.getName());
		statement.setString(2, employee.getAdress());
		statement.setInt(3, employee.getOfficeId());
		System.out.println("\nDone\n");
		return statement.executeUpdate();
	}

	@Override
	public List<Employee> findAll() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from employees");
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("employee_id"));
			employee.setName(rs.getString("name"));
			employee.setAdress(rs.getNString("adress"));
			employees.add(employee);
		}
		return employees;
	}

	@Override
	public Employee find(Object id) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from employees where employee_id = ?");
        statement.setInt(1, (int)id);
        ResultSet rs = statement.executeQuery();
        Employee employee = null;
        if(rs.next()) {
        	employee = new Employee();
        	employee.setEmployeeId(rs.getInt("employee_id"));
        	employee.setName(rs.getString("name"));
        	employee.setAdress("adress");
        }
        return employee;
	}

	@Override
	public int update(Employee employee) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();	
		PreparedStatement statement = connection.prepareStatement("update employees set name = ?, adress = ?, email = ? where employee_id = ?");
		statement.setString(1, employee.getName());
		statement.setString(2, employee.getAdress());
		statement.setInt(3, employee.getOfficeId());
		statement.setInt(4, employee.getEmployeeId());
		System.out.println("Updated");
		return statement.executeUpdate();
	}

	@Override
	public int delete(Employee employee) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from employees where employee_id = ?");
		statement.setInt(1, employee.getEmployeeId());
		System.out.println("Deleted");
		return statement.executeUpdate();
	}
	
	

}
