package com.tek.totalsmanager.order.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

/**
 * Represents an item, contains a price and a description.
 *
 */
class Item {

	private String description;
	private float price;

	/**
	 * Constructor which will validate request parameters and initialize item
	 * description and item price
	 * 
	 * @param description
	 *            - Item description
	 * 
	 * @param price
	 *            - Item price
	 */
	public Item(String description, float price) throws Exception {
		// we don't need to write super() here, as this will be there default
		//super();
		
		//Bug : we need to validate description and price
		// validate item request parameters
		validateItemReqParams(description, price);
		this.description = description;
		this.price = price;
	}

	/**
	 * Get the description of item
	 * 
	 * @return - returns the description of item
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the price of item
	 * 
	 * @return - returns the price of item
	 * 
	 */
	public float getPrice() {
		return price;
	}
	
	/**
	 * Method to validate Order line item request parameters
	 * 
	 * @param description
	 *            - description of item
	 * @param price
	 *            - price of item
	 * @throws Exception
	 *             - if description is passed as null or price is not greater
	 *             than '0'
	 */
	private void validateItemReqParams(String description, float price)
			throws Exception {
		if (description == null) {
			System.err.println("ERROR - Item description is NULL");
			throw new Exception("Item description is NULL");
		}
		if (price <= 0) {
			System.err.println("ERROR - Item price is " + price + ", which is not a valid price");
			throw new Exception("Price is not greater than 0");
		}
	}
}

/**
 * Represents an order line which contains the @Item and the quantity. //Bug:
 * reference to class Item was incorrect
 *
 */
class OrderLine {

	private int quantity;
	private Item item;
	
	/**
	 * Constructor to validate item and quantity of order line and initialize
	 * item and quantity
	 * 
	 * @param item
	 *            Item of the order
	 * 
	 * @param quantity
	 *            Quantity of the item
	 */
	public OrderLine(Item item, int quantity) throws Exception {
		// validate order line parameters
		validateOrderLineReqParams(item, quantity);
		
		// Bug: this will not throw exception even if we pass quantity <= '0'
		assert quantity > 0;
		
		// Bug Found: Not updating class variable 'item'
		// item = item;
		this.item = item;

		// Bug Found: Not updating class variable 'quantity'
		// quantity = quantity;
		this.quantity = quantity;
				
	}

	/**
	 * Get the Item, which includes description and price
	 * 
	 * @return - Item, which includes description and price
	 * 
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Get the quantity of item
	 * 
	 * @return - the quantity of item
	 * 
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Method to validate order line parameters
	 * 
	 * @param item
	 *            - item object which includes description and price
	 * @param quantity
	 *            - quantity of item
	 * @throws Exception
	 *             - if either item object is null or quantity is <= 0
	 */
	private void validateOrderLineReqParams(Item item, int quantity)
			throws Exception {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
			throw new Exception("Item is NULL");
		}
		if (quantity <= 0) {
			System.err.println("ERROR - quantity is not greater than 0");
			throw new Exception("quantity is not greater than 0");
		}
	}
}

/**
 * Represents an order line which contains the @OrderLine details.
 *
 */
class Order {

	// Bug: NullPointerException thrown when adding an OrderLine because it was not instantiated first.
	// private List<OrderLine> orderLines;
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	/**
	 * Method to add order lines to order
	 * 
	 * @param o
	 *            - orderLine object which includes item and quantity
	 * @throws Exception
	 *             - throws exception when orderLine object is null
	 */
	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	/**
	 * Get the size of orderLines
	 * 
	 * @return - the size of orderLines
	 * 
	 */
	public int size() {
		return orderLines.size();
	}

	/**
	 * Get the order line of index passed from list
	 * 
	 * @return - current order line from list
	 * 
	 */
	public OrderLine get(int i) {
		return orderLines.get(i);
	}
	
	// No need of this method
	public void clear() {
		this.orderLines.clear();
	}
	
	/**
	 * Method to validate order line details
	 * 
	 * @param orderLine
	 *            - orderLine object which includes item and quantity
	 * @throws Exception
	 *             - throws exception if orderLine is null
	 */
	static void validateOrderLineDetails(OrderLine orderLine)
			throws Exception {
		if (orderLine == null) {
			System.err.println("ERROR - OrderLine is NULL");
			throw new IllegalArgumentException("OrderLine is NULL");
		}
	}
}

/**
 * To calculate total price of the each order
 *
 */
class Calculator {

	/**
	 * Method for rounding value to two decimals
	 * @param value
	 * @return
	 */
	public static double rounding(double value) {
		/*
		 * Bug: Dividing an integer by an integer results in the return value
		 * being an integer cast to a double. Also, we should be utilizing
		 * BigDecimal to avoid any internal rounding that may occur.
		 */
		//return ( (int) (value * 100)) / 100;
		return new BigDecimal(value).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
	}

	/**
	 * Receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's (price *
	 * quantity) + taxes Bug: (price * quantity) + taxes is not correct while
	 * calculating items price
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 * 
	 * @throws Exception - if either order or order line is null
	 */
	public void calculate(Map<String, Order> o) throws Exception {

		double grandtotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
			
			// Bug: grandtotal should not be reset to 0 every order.
			// grandtotal = 0;

			Order r = entry.getValue();
			
			// Bug: we should validate order & number of lines before iterating
			validateOrders(r);

			double totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			// Bug: It will throw ArrayOutofBound exception
			//for (int i = 0; i <= r.size(); i++) {
			for (int i = 0; i < r.size(); i++) {
				
				/*
				 * Bug: we should validate each order line details before
				 * getting values from it or else we may end up in null pointer
				 * exception
				 */
				Order.validateOrderLineDetails(r.get(i));

				// Calculate the taxes
				double tax = 0;
				
				// Bug:Item description comparisons should be case insensitive
				//if (r.get(i).getItem().getDescription().contains("imported")) {
			if (r.get(i).getItem().getDescription().toLowerCase().contains(("imported"))) {
				
				// Bug: quantity was not considered while calculating tax
				//tax = rounding(r.get(i).getItem().getPrice() * 0.15);
					tax = rounding(r.get(i).getItem().getPrice() * r.get(i).getQuantity() * 0.15); // Extra 5% tax on imported items
				} else {
					tax = rounding(r.get(i).getItem().getPrice() * r.get(i).getQuantity() * 0.10); // 10% tax on regular items
				}
				// Calculate the total price
				// Bug: Math.Floor removes any precision beyond the decimal. Here also we need to consider quantity
				//double totalprice = r.get(i).getItem().getPrice() + Math.floor(tax);
				double totalprice = rounding((r.get(i).getItem().getPrice() * r.get(i).getQuantity()) + tax);

				// Print out the item's total price
				// Bug Found: Math.Floor removes any precision beyond the decimal.
				System.out.println(r.get(i).getQuantity() + " " + r.get(i).getItem().getDescription() + ": " + totalprice);

				// Keep a running total
				totalTax += tax;
				total += (r.get(i).getItem().getPrice() * r.get(i).getQuantity());
			}

			// Print out the total taxes
			// Bug: Math.Floor removes any precision beyond the decimal.
			//System.out.println("Sales Tax: " + Math.floor(totalTax));
			System.out.println("Sales Tax: " + rounding(totalTax));

			// Bug : Total should represent the price of all the items minus their sales tax.
			//total = total + totalTax;

			// Print out the total amount
			// Bug Found: Math.Floor removes any precision beyond the decimal.
			//System.out.println("Total: " + Math.floor(total * 100) / 100);
			System.out.println("Total: " + rounding(total));
			grandtotal += total;
		}

		// Bug Found: Math.Floor removes any precision beyond the decimal.
		//System.out.println("Sum of orders: " + Math.floor(grandtotal * 100) / 100);
		System.out.println("Sum of orders: " + rounding(grandtotal));
	}
	
	/**
	 * Method to validate order and throws exception if validation fails
	 * 
	 * @param r
	 *            - holds order object
	 * @throws Exception
	 *             - if order object is null or order line size is not greater
	 *             than '0'
	 */
	private static void validateOrders(Order r) throws Exception {
		if (r == null) {
			System.err.println("ERROR - cannot calculate Order is NULL or empty");
			throw new IllegalArgumentException("Order is NULL or empty");
		} else if (!(r.size() >  0)) {
			System.err.println("ERROR - OrderLine is NULL");
			throw new IllegalArgumentException("OrderLine is NULL");
		}
	}
}

/**
 * Represents Foo which will be the entry point of calculating order totals
 *
 */
public class Foo {

	public static void main(String[] args) throws Exception {

		
		/*
		 * Bug: HashMap doesn't guarantee the order of insertion, instead
		 * LinkedHashMap does
		 */
		// Map<String, Order> o = new HashMap<String, Order>();
		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order c = new Order();

		/*
		 * we don't need this variable since grandTotal is computed in
		 * Calculator class
		 */
		//double grandTotal = 0;

		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		o.put("Order 1", c);

		// Reuse cart for an other order
		// Bug: Since 'c' is a reference variable, any changes done to c will reflect in all orders
		//c.clear();
		c = new Order();
			
		c.add(new OrderLine(new Item("imported box of chocolate", (float) 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		o.put("Order 2", c);

		// Reuse cart for an other order
		// Bug: Since 'c' is a reference variable, any changes done to c will reflect in all orders
		//c.clear();
		c = new Order();

		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		
		// Bug: Imported misspelled.
		c.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		o.put("Order 3", c);

		// Bug: Class name should start with Upper case letters
		new Calculator().calculate(o);


	}
}
