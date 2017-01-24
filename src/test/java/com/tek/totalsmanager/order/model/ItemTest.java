package com.tek.totalsmanager.order.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import com.tek.totalsmanager.order.constants.ItemType;
import com.tek.totalsmanager.order.model.Item;

public class ItemTest {

	@Test
	public void validItemDataTest() throws Exception {

		Item item = new Item("Apple", 2.5d);
		assertNotNull(item);
		assertEquals(item.getDescription(), "Apple");
		assertEquals(item.getItemType(), ItemType.NORMAL);
		assertEquals(2.5,item.getPrice(), 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void itemWithNoDescriptionTest() throws IllegalArgumentException {
		 new Item(null, 2.5d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void itemWithZeroPriceTest() throws IllegalArgumentException {
		 new Item("Apple", 0);
	}
	
	@Test
	public void importedItemTest() throws IllegalArgumentException {
		Item item = new Item("Imported Apple", 3d);
		Assert.assertSame(item.getItemType(), ItemType.IMPORTED);
	}
}
