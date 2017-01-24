package com.tek.totalsmanager.order.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tek.totalsmanager.order.service.spi.CartService;

public class CartServiceImplTest {
	
	private static CartService cartService = null;

	@Before
	public void setUp() throws Exception
	{
		cartService = new CartServiceImpl();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		cartService = null;
	}
	
	@Test
	public void testCartCreation() throws Exception {
		Assert.assertNotNull(cartService.startSession());
		Assert.assertEquals(cartService.startSession(), cartService.startSession());
	}
	
	@Test(expected=NullPointerException.class)
	public void testEmptyCartCheckoutOrder(){
		cartService.checkOutOrder();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullCartServiceCartCheckoutOrder(){
		cartService = null;
		cartService.checkOutOrder();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullCartCheckoutOrder(){
		cartService.checkOutOrder();
	}
	
	@Test
	public void testAddItem() throws Exception{
		cartService.startSession().addItem("Apple", 0.1, 1);
		cartService.checkOutOrder();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullCartPrintSummary() throws Exception{
		
		cartService.printSummary();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCartPrintSummary() throws Exception{
		cartService.startSession();
		cartService.printSummary();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyCartCheckoutPrintSummary() throws Exception{
		cartService.startSession().addItem("Apple", 0.1, 1);
		cartService.printSummary();
	}
	
	@Test
	public void testCartPrintSummary() throws Exception{
		cartService.startSession().addItem("Apple", 0.1, 1);
		cartService.startSession().checkOutOrder();
		cartService.printSummary();
	}
	
}
