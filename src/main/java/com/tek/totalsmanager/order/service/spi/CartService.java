package com.tek.totalsmanager.order.service.spi;

import com.tek.totalsmanager.order.dto.Cart;

/**
 * Interface used to create session, checkout process and print summary
 * 
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public interface CartService {
	
	/**
	 * Method to create cart
	 * 
	 * @return - returns the cart created
	 * 
	 */
    Cart startSession();

    /**
	 * Method to checkout the order for current created cart
	 * 
	 */
    void checkOutOrder();

    /**
	 * Method to print summary for all orders
	 * 
	 */
    void printSummary();
}
