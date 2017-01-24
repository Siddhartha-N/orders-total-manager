package com.tek.totalsmanager.order.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private static Order order = null;

	@Before
	public void setUp() throws Exception
	{
		order = new Order();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		order = null;
	}
	
	@Test
	public void testOrderCreation() throws Exception {
		Assert.assertNotNull(order.getOrderName());
		Assert.assertNotNull(order.getOrderLines());
		Assert.assertTrue(order.getOrderLines().isEmpty());
	}
	
	@Test
	public void testMultiOrderCreation() throws Exception {
		Order secondOrder = new Order();
		Assert.assertNotEquals(order, secondOrder);
		Assert.assertNotEquals(order.getOrderName(), secondOrder.getOrderName());
	}
	
	@Test
	public void testAddOrderLines() throws Exception {
		Assert.assertTrue(order.getOrderLines().isEmpty());
		order.add(new OrderLine(new Item("Apple", 0.1), 1));
		Assert.assertFalse(order.getOrderLines().isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullAddOrderLines(){
		order.add(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidateOrderLineDetails(){
		Order.validateOrderLineDetails(order.getOrderLines());
	}
	
}
