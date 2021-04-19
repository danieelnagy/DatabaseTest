package entities;

import javax.persistence.*;

@Entity
@Table(name = "warehouses")
public class WareHouse {


	@Id
	@Column(name = "warehouse_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareHouseId;
	@Column(name = "name")
	private String name;
	@Column(name = "adress")
	private String adress;
	
	public WareHouse() {
		
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
