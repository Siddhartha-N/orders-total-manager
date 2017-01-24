package com.tek.totalsmanager.order.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents an order line which contains the @OrderLine details.
 *@author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class Order {

	private static int number = 1;
	// holds order line details
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	// holds order number
	private final String orderNumber;
	
	/**
	 * Default constructor to create Order number
	 */
	public Order(){
		this.orderNumber = "Order " + number;
		number++;
	}
	
	/**
	 * Get the list of orderLines
	 * 
	 * @return - the list of orderLines
	 * 
	 */
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	/**
	 * Get the order number
	 * 
	 * @return - the order number
	 * 
	 */
	public String getOrderName() {
		return orderNumber;
	}

	/**
	 * Method to add order lines to order
	 * 
	 * @param orderLine
	 *            - orderLine object which includes item and quantity
	 * @throws IllegalArgumentException
	 *             - throws exception when orderLine object is null
	 */
	public void add(OrderLine orderLine) throws IllegalArgumentException {
		if (orderLine == null) {
			throw new IllegalArgumentException("OrderLine cannot be NULL");
		}
		orderLines.add(orderLine);
	}
	
	/**
	 * Method to validate order lines
	 * 
	 * @param orderLine
	 *            - list of order lines
	 * @throws IllegalArgumentException
	 *             - if order line is empty
	 */
	public static void validateOrderLineDetails(List<OrderLine> orderLines)
			throws IllegalArgumentException {
		if (orderLines.isEmpty()) {
			throw new IllegalArgumentException("OrderLine is NULL");
		}
	}
}

