package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "complaints")
public class Complaint {

	@Id
	@Column(name = "complaint_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complaint_id;
	@Column(name = "product_id")
	private int product_id;
	@Column(name = "customer_id")
	private int customer_id;
	@Column(name = "employee_id")
	private int employee_id;
	@Column(name = "complaint_date")
	private Date date;
	@Column(name = "describe_complaint")
	private String describe;
	
	
	public Complaint() {
		
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
