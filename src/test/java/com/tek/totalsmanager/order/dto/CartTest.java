package com.tek.totalsmanager.order.dto;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tek.totalsmanager.order.dto.Cart;
import com.tek.totalsmanager.order.main.ShoppingCart;
import com.tek.totalsmanager.order.service.impl.CartServiceImpl;
import com.tek.totalsmanager.order.service.spi.CartService;

public class CartTest {
	
	private static Cart cart = null;

	@Before
	public void setUp() throws Exception
	{
		cart = new Cart();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		cart = null;
	}
	
	@Test
	public void ShoppingCartTest() throws Exception {
		ShoppingCart.main(null);
	}
	
	@Test
	public void printSummaryTest() throws Exception {
		CartService cartService = new CartServiceImpl();

		Cart cart = cartService.startSession();

		cart.addItem("book", 12.49d, 1);
		cart.addItem("music CD", 14.99d, 1);
		cart.addItem("music CD", 14.99d, 1);
		cartService.checkOutOrder();

		cart.addItem("imported box of chocolate", 10, 1);
		cart.addItem("imported bottle of perfume", 47.50d, 1);
		cart.addItem("music CD", 14.99d, 1);
		cartService.checkOutOrder();

		cart.addItem("Imported bottle of perfume", 27.99d, 1);
		cart.addItem("bottle of perfume", 18.99d, 1);
		cart.addItem("packet of headache pills", 9.75d, 1);
		cart.addItem("box of imported chocolates", 11.25d, 1);
		cartService.checkOutOrder();

		cartService.printSummary();
	}
	
	@Test
	public void testCartObjectCreation(){
		
		assertNotNull(cart.getOrderHistory());
	}
	
	@Test
	public void testAddItem() throws Exception{
		
		cart.addItem("Apple", 0.1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddItemNullItemName() throws Exception{
		
		cart.addItem(null, 0.1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddItemEmptyItemName() throws Exception{
		
		cart.addItem("", 0.1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddItemZeroItemPrice() throws Exception{
		
		cart.addItem("Apple", 0, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddItemZeroItemQuantity() throws Exception{
		
		cart.addItem("Apple", 0.1, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullCartAddItem() throws Exception{
		cart = null;
		cart.addItem("Apple", 0.1, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCheckOutOrderNullCart() throws Exception{
		cart = null;
		cart.checkOutOrder();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckOutOrderEmptyCart() throws Exception{
		cart.checkOutOrder();
	}
	
	@Test
	public void testCheckOutOrder() throws Exception{
		cart.addItem("Apple", 0.1, 1);
		cart.checkOutOrder();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullCartGetOrderHistory() throws Exception{
		cart = null;
		cart.getOrderHistory();
	}
	
	@Test
	public void testGetEmptyOrderHistory() throws Exception{
		assertNotNull(cart.getOrderHistory());
	}
	
	@Test
	public void testGetOrderHistory() throws Exception{
		cart.addItem("Apple", 0.1, 1);
		assertNotNull(cart.getOrderHistory());
	}
}
