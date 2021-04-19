package entities;

import java.sql.Date;

public class Order {
	
	private int orderId;
	private int customerId;
	private int productId;
	private int stock;
	private Date orderDate;
	private Date shipmentDate;
	private Date arrivalDate;
	
	public Order() {
		
	}
	
	public Order(int orderId, int customerId, int productId, int stock, Date orderDate, Date shipmentDate,
			Date arrivalDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.productId = productId;
		this.stock = stock;
		this.orderDate = orderDate;
		this.shipmentDate = shipmentDate;
		this.arrivalDate = arrivalDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	
	
	
	
}
