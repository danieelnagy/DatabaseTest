package entities;

import java.sql.Date;

public class Complaint {

	
	private int complaint_id;
	private int product_id;
	private int customer_id;
	private int employee_id;
	private Date date;
	private String describe;
	
	
	public Complaint() {
		
	}
	
	public Complaint(int complaint_id, int product_id, int customer_id, int employee_id, Date date, String describe) {
		super();
		this.complaint_id = complaint_id;
		this.product_id = product_id;
		this.customer_id = customer_id;
		this.employee_id = employee_id;
		this.date = date;
		this.describe = describe;
	}

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
	
}
