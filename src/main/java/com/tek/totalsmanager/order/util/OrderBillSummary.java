package com.tek.totalsmanager.order.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.tek.totalsmanager.order.constants.ItemType;
import com.tek.totalsmanager.order.model.Order;
import com.tek.totalsmanager.order.model.OrderLine;

/**
 * Class to calculate total price of the each order
 *
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
final public class OrderBillSummary {
	
	private static final double IMPORTED_TAX_RATE_DECIMAL_VALUE = 0.15;
	private static final double NORMAL_TAX_RATE_DECIMAL_VALUE = 0.10;
	

	/**
	 * Restricting to instantiate this class. 
	 */
    private OrderBillSummary() {
    }
	
	/**
	 * Receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's (price *
	 * quantity) + taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 * 
	 * @param - List of orders
	 * @throws IllegalArgumentException
	 *             - if any order or order line is NULL
	 */
	public static void calculate(List<Order> orderList)
			throws IllegalArgumentException {

		// validate list of orders
		validateOrders(orderList);

		double grandtotal = 0;

		// Iterate through the orders
		for (Order order : orderList) {
			System.out.println("*******" + order.getOrderName() + "*******");

			// method to calculate oeach order tax & total amount
			double orderItemsTotalAmt = calculateOrderTotalAmt(order);

			// calculating total amount for all orders
			grandtotal += orderItemsTotalAmt;
		}

		System.out.println("Sum of orders: "
				+ roundingToTwoDecimals(grandtotal));
	}

	/**
	 * Method to calculate each item price, order's Sales tax & order total
	 * without tax
	 * 
	 * @param order
	 *            - holds order details
	 * @return - total amount of order
	 */
	private static double calculateOrderTotalAmt(Order order) {
		
		double orderTotalTaxAmt = 0;
		double orderItemsTotalAmt = 0;

		// Iterate through each item in the order
		for (OrderLine orderLine : order.getOrderLines()) {

			double itemTaxAmt = 0;
			// Calculate the total quantity price of the item
			double itemPrice = orderLine.getItem().getPrice()
					* orderLine.getQuantity();

			// method to calculate tax
			itemTaxAmt = calculateTax(orderLine, itemPrice);
			/*
			 * Print out the item's total price = total quantity price + tax for
			 * the item
			 */
			System.out.println(orderLine.getQuantity() + " "
					+ orderLine.getItem().getDescription() + ": "
					+ roundingToTwoDecimals(itemPrice + itemTaxAmt));

			// Keep a running total of the order
			orderTotalTaxAmt += itemTaxAmt;
			orderItemsTotalAmt += itemPrice;
		}

		// Print out the total taxes of the order
		System.out.println("Sales Tax: "
				+ roundingToTwoDecimals(orderTotalTaxAmt));

		// Print out the total amount of the order without tax
		System.out.println("Total: "
				+ roundingToTwoDecimals(orderItemsTotalAmt));
		
		return orderItemsTotalAmt;
	}

	/**
	 * Method to calculate tax based on item type, item price and ordered item
	 * quantity
	 * 
	 * @param orderLine
	 *            - holds line details of order
	 * @param itemPrice
	 *            - holds price of item
	 * @return - calculated tax amount
	 */
	private static double calculateTax(OrderLine orderLine, double itemPrice) {
		
		double itemTaxAmt;
		if (orderLine.getItem().getItemType().equals(ItemType.IMPORTED)) {
			// Extra 5% tax on imported items
			itemTaxAmt = roundingToTwoDecimals(itemPrice
					* IMPORTED_TAX_RATE_DECIMAL_VALUE);
		} else {
			// 10% tax on regular items
			itemTaxAmt = roundingToTwoDecimals(itemPrice
					* NORMAL_TAX_RATE_DECIMAL_VALUE);
		}
		return itemTaxAmt;
	}
    
	/**
	 * Method to validate orders and order line details
	 * 
	 * @param orderList
	 *            - list of orders
	 * @throws IllegalArgumentException
	 *             - If either Order or orderLine is null
	 */
	private static void validateOrders(List<Order> orderList)
			throws IllegalArgumentException {
		if (orderList != null && !orderList.isEmpty()) {
			for (Order order : orderList) {
				Order.validateOrderLineDetails(order.getOrderLines());
			}
		} else {
			throw new IllegalArgumentException("Supplied order is Null");
		}
	}
	
	/**
	 * Method for rounding value to two decimals
	 * 
	 * @param valueToRound
	 *            - holds value to round
	 * @return - value which is rounded to two decimals
	 */
	public static double roundingToTwoDecimals(double valueToRound) {
		return new BigDecimal(valueToRound).setScale(2, RoundingMode.HALF_DOWN)
				.doubleValue();
	}
}
