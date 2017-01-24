package com.tek.totalsmanager.order.dto;

import com.tek.totalsmanager.order.model.Item;
import com.tek.totalsmanager.order.model.Order;
import com.tek.totalsmanager.order.model.OrderLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to add items to current order and maintain order history
 * 
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class Cart {

	// holds order history
	private List<Order> orderHistory;
	// holds current order details
	private Order currentOrder;

	/**
	 * Method to initialize the order and order history
	 */
	public Cart() {
		orderHistory = new ArrayList<Order>();
		currentOrder = new Order();
	}

	/**
	 * Method to add order line item which will have item description, price and
	 * quantity
	 */
	public void addItem(String itemName, double unitPrice, int quantity)
			throws Exception {
		addOrderItem(new OrderLine(new Item(itemName, unitPrice), quantity));
	}

	/**
	 * Method to add created order line item to current order
	 * 
	 * @param orderLine
	 *            - includes item and quantity
	 * @throws Exception
	 *             - throws exception if any required data is missing
	 */
	private void addOrderItem(OrderLine orderLine) throws Exception {
		currentOrder.add(orderLine);
	}

	/**
	 * Method to add current order to order history, which will be used later to
	 * print summary
	 */
	public void checkOutOrder() {
		if(!currentOrder.getOrderLines().isEmpty()){
			orderHistory.add(currentOrder);
			currentOrder = new Order();
		}else{
			throw new IllegalArgumentException(
					"Curernt Order is empty to checkout");
		}
	}

	/**
	 * Method to get created orders history
	 * 
	 * @return - returns list of placed orders
	 * 
	 */
	public List<Order> getOrderHistory() {
		return orderHistory;
	}
}
