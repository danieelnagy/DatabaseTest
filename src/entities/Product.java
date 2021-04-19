package entities;

public class Product {

	private int productId;
	private String name;
	private String description;
	private double price;
	private int stock;
	private int wareHouseId;
	private int positionId;

	
	public Product() {
		
	}
	
	public Product(int productId, String name, String description, double price, int stock, int wareHouseId,
			int positionId) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.wareHouseId = wareHouseId;
		this.positionId = positionId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(int wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

}
