package entities;

public class WareHouse {

	private int wareHouseId;
	private String name;
	private String adress;
	
	public WareHouse() {
		
	}

	public WareHouse(int wareHouseId, String name, String adress) {
		this.wareHouseId = wareHouseId;
		this.name = name;
		this.adress = adress;
	}

	public int getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(int wareHouseId) {
		this.wareHouseId = wareHouseId;
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
