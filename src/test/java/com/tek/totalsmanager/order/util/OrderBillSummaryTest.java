package com.tek.totalsmanager.order.util;

import org.junit.Assert;
import org.junit.Test;

import com.tek.totalsmanager.order.dto.Cart;
import com.tek.totalsmanager.order.service.impl.CartServiceImpl;
import com.tek.totalsmanager.order.service.spi.CartService;
import com.tek.totalsmanager.order.util.OrderBillSummary;

public class OrderBillSummaryTest {

	
	@Test
	public void validItemDataTest() throws Exception {

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
		
		OrderBillSummary.calculate(cart.getOrderHistory());
		
		Assert.assertNotNull(cart.getOrderHistory());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nullOrdersTest() throws Exception {
		
		OrderBillSummary.calculate(null);
		
	}
	
	@Test
	public void roundingToTwoDecimalsTest() throws Exception {

		OrderBillSummary.roundingToTwoDecimals(12.78976);

		Assert.assertEquals((long) 12.79,
				(long) OrderBillSummary.roundingToTwoDecimals(12.78976));

	}
}
