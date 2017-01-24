package com.tek.totalsmanager.order.service.impl;

import com.tek.totalsmanager.order.dto.Cart;
import com.tek.totalsmanager.order.service.spi.CartService;
import com.tek.totalsmanager.order.util.OrderBillSummary;

/**
 * Class used to create session, checkout process and print summary
 * 
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class CartServiceImpl implements CartService {
    private Cart cart;

    /**
	 * Method to create cart
	 * 
	 * @return - returns the cart created
	 * 
	 */
    public Cart startSession() {
    	if(cart == null){
    		cart = new Cart();
    	}
        return cart;
    }

	/**
	 * Method to checkout the order for current created cart
	 * 
	 */
    public void checkOutOrder() {
        cart.checkOutOrder();
    }

	/**
	 * Method to print summary for all orders
	 * 
	 */
	public void printSummary() {
		OrderBillSummary.calculate(cart.getOrderHistory());
	}
}
