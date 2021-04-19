package entities;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office {

	@Id
	@Column(name = "office_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int officeId;
	@Column(name = "name")
	private String name;
	@Column(name = "adress")
	private String adress;




	public Office() {
		
	}
	
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
}
