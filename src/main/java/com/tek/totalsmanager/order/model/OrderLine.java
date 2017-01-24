package com.tek.totalsmanager.order.model;


/**
 * Class which represents an order line which contains the @Item and the
 * quantity.
 * 
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class OrderLine {

	// holds quantity of order line
    private int quantity;
    // holds Item of order line
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
        this.item = item;
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
	 * @throws IllegalArgumentException
	 *             - if either item object is null or quantity is <= 0
	 */
	private void validateOrderLineReqParams(Item item, int quantity)
			throws IllegalArgumentException {

		if (item == null) {
			throw new IllegalArgumentException(
					"Supplied item cannot be null, please add valid item");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Supplied quantity is not greater than 0");
		}
	}
}

