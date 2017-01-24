package com.tek.totalsmanager.order.main;

import com.tek.totalsmanager.order.dto.Cart;
import com.tek.totalsmanager.order.service.impl.CartServiceImpl;
import com.tek.totalsmanager.order.service.spi.CartService;

/**
 * Class which is used for checkout process like creating cart, adding orders
 * and printing order summary
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class ShoppingCart {

	/**
	 * Main entry point for the second version of the OrderCalculation interview
	 * question.
	 * 
	 * @param args
	 *            - no arguments used
	 */
	public static void main(String[] args) throws Exception {

		try {

			CartService cartService = new CartServiceImpl();

			Cart cart = cartService.startSession();

			cart.addItem("book", 12.49d, 1);
			cart.addItem("music CD", 14.99d, 1);
			cart.addItem("chocolate bar", 0.85d, 1);
			cartService.checkOutOrder();

			cart.addItem("imported box of chocolate", 10d, 1);
			cart.addItem("imported bottle of perfume", 47.50d, 1);
			cartService.checkOutOrder();

			cart.addItem("Imported bottle of perfume", 27.99d, 1);
			cart.addItem("bottle of perfume", 18.99d, 1);
			cart.addItem("packet of headache pills", 9.75d, 1);
			cart.addItem("box of imported chocolates", 11.25d, 1);
			cartService.checkOutOrder();

			cartService.printSummary();
			
		} catch (Exception exception) {
			System.err.printf("Exception occured while placing order " + exception);
		}

	}
}
