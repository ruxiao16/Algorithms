package com.xiao.algorithms.trade;

public class Order {
	int orderId;
	double price;
	int quantity;

	public Order (int orderId, double price, int quantity) {
		this.orderId = orderId;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + orderId + ", price=" + price + ", qty=" + quantity + '}';
	}
}
