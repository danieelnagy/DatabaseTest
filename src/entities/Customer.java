package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int customerId;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "adress")
	private String adress;
	@Column(name = "comments")
	private String comments;
	@Column(name = "organisation")
	private String organisation;
	@Column(name = "saleprecentage")
	private int salePercentage;

	
	public Customer() {
		
	}
		
	public Customer(int customerId, String name, String email, String adress, String comments, String organisation,
			int salePercentage) {
		
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.adress = adress;
		this.comments = comments;
		this.organisation = organisation;
		this.salePercentage = salePercentage;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public int getSalePercentage() {
		return salePercentage;
	}
	public void setSalePercentage(int salePercentage) {
		this.salePercentage = salePercentage;
	}
	
}
