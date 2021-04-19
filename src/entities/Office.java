package entities;

public class Office {

	private int officeId;
	private String name;
	private String adress;
	
	public Office() {
		
	}
	
	public Office(int officeId, String name, String adress) {
		this.officeId = officeId;
		this.name = name;
		this.adress = adress;
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
