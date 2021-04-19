package entities;

public class Employee {

	private int EmployeeId;
	private String name;
	private String email;
	private String adress;
	private String comments;
	private int officeId;
	
	public Employee() {
		
	}
	
	
	public Employee(int employeeId, String name, String email, String adress, String comments, int officeId) {
		EmployeeId = employeeId;
		this.name = name;
		this.email = email;
		this.adress = adress;
		this.comments = comments;
		this.officeId = officeId;
	}

	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	
	
	
	
}
