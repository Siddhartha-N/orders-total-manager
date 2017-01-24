package com.tek.totalsmanager.order.model;


import com.tek.totalsmanager.order.constants.ItemType;

/**
 * Class which represents an item, contains item type, price and description of item.
 *@author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class Item {
	// holds item type of item
    private ItemType itemType = ItemType.NORMAL;
    // holds description of item
    private String description;
    // holds price of item
    private double price;

	/**
	 * Constructor which will validate request parameters and initialize item
	 * type, item description and item price
	 * 
	 * @param description
	 *            - Item description
	 * 
	 * @param price
	 *            - Item price
	 */
    public Item(String description, double price) throws IllegalArgumentException{
    	
    	// validate item request parameters
    	validateItemReqParams(description, price);
        if (description.toLowerCase().contains("imported")) {
            itemType = ItemType.IMPORTED;
        }
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
    public double getPrice() {
        return price;
    }

    /**
	 * Get the type of item
	 * 
	 * @return - returns the type of item
	 * 
	 */
    public ItemType getItemType() {
        return itemType;
    }

    /**
	 * Method to validate Order line item request parameters
	 * 
	 * @param description
	 *            - description of item
	 * @param price
	 *            - price of item
	 * @throws IllegalArgumentException
	 *             - if description is passed as null or price is not greater
	 *             than '0'
	 */
	private void validateItemReqParams(String description, double price)
			throws IllegalArgumentException {
		
		if (description == null || description.equals("")) {
			throw new IllegalArgumentException(
					"Supplied item description cannot be NULL");
		}
		if (price <= 0) {
			throw new IllegalArgumentException(
					"Supplied price of item is not greater than 0");
		}
	}
}

