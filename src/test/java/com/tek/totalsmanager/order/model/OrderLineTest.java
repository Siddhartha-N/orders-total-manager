package com.tek.totalsmanager.order.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.tek.totalsmanager.order.constants.ItemType;


/**
 * Class which represents an order line which contains the @Item and the
 * quantity.
 * 
 * @author Siddhartha (siddhartha.sm81@gmail.com)
 */
public class OrderLineTest {
	
	
	/*
	 * Test Scenario 1 - To check the order line creation by passing all valid
	 * values and description as imported for item description
	 */
	@Test
	public void testWithValidValuesForImportedDesc() throws Exception {
		// Scenario 1
		String descrption = "Books imported";
		double price = 75.8;
		int quantity = 2;
		Item item = new Item(descrption, price);
		OrderLine orderLine = new OrderLine(item, quantity);
		// order line object should not be null
		assertNotNull(orderLine);
		// item object should not be null
		assertNotNull(item);
		// quantity should be 2
		assertEquals(orderLine.getQuantity(), quantity);
		// price should be 75.8
		assertEquals(price, orderLine.getItem().getPrice(), 0.0);
		// description should be Books Imported
		assertEquals(descrption, orderLine.getItem().getDescription());
		// Item Type should be Imported since the descrption contains imported
		assertEquals(ItemType.IMPORTED, orderLine.getItem().getItemType());

	}
	
	/*
	 * Test Scenario 2 - To check the order line creation by passing all valid
	 * values and description as normal for item
	 */
	@Test
	public void testOrderLineWithValidValuesForNormalDesc() throws Exception {
		// Scenario 2
		String descrption = "Books";
		double price = 75.8;
		int quantity = 2;
		Item item = new Item(descrption, price);
		OrderLine orderLine = new OrderLine(item, quantity);
		// order line object should not be null
		assertNotNull(orderLine);
		// item object should not be null
		assertNotNull(item);
		// quantity should be 2
		assertEquals(quantity, orderLine.getQuantity());
		// price should be 75.8
		// price should be 75.8
		assertEquals(price, orderLine.getItem().getPrice(), 0.0);
		// description should be Books Imported
		assertEquals(descrption, orderLine.getItem().getDescription());
		// Item Type should be Imported since the descrption contains imported
		assertEquals(ItemType.NORMAL, orderLine.getItem().getItemType());
	}
	
	/*
	 * Test Scenario 3 - To check the order line creation by passing no item
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testOrderLineWithNullItem() throws Exception {
		// Scenario 2
		int quantity = 2;
		OrderLine orderLine = new OrderLine(null, quantity);
	}
	
	/*
	 * Test Scenario 4 - To check the order line creation by passing zero quantity
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testOrderLineWithZeroQuantity() throws Exception {
		String descrption = "Books";
		double price = 75.8;
		Item item = new Item(descrption, price);
		OrderLine orderLine = new OrderLine(item, 0);
	}

}