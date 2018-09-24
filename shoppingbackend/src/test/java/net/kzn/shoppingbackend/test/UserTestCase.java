package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	/*
	@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		//add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		//link the user with address using user id
		address.setUserId(user.getId());
			
		
		//add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));
		
		
		if(user.getRole().equals("USER")) {
			
			// create a cart for this user
			cart=new Cart();
			cart.setUser(user);
			
			assertEquals("Failed to add the address!", true, userDAO.addCart(cart));
			
			//add the shipping address for this user
			
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shipping to true
			address.setShipping(true);
			
			//link with the user
			
			address.setUserId(user.getId());
			//add the shipping address
			assertEquals("Failed to add shipping the address!", true, userDAO.addAddress(address));
			
		}
		
		
		
		
	}
	
	*/
	
	/*@Test
	public void testAddUser() {
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		if(user.getRole().equals("USER")) {
			
			// create a cart for this user
			cart=new Cart();
			cart.setUser(user);
			
			//attache car with the user
			
			user.setCart(cart);
			
		}
		
		//add the user
				assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	}
	
*/

	/*@Test
	public void testUpadteCart() {
		
		
		//fetch user by it's id
		user=userDAO.getByEmail("hr@gmail.com");
	
		//get the cart of the user
		cart=user.getCart();
		
		cart.setGrandTotal(5555);
		
		cart.setCartLines(2);
		assertEquals("Failed to upadte the cart!", true, userDAO.updateCart(cart));
		
	}
	
	*/
	
	
	/*@Test
	public void testAddAddress() {
		
		//ew need to add the user
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		//add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		//we are going add to address

		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attach the user with the address
		
		address.setUser(user);
		assertEquals("Failed to add the user!", true, userDAO.addAddress(address));
		
		
		//we are going to add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		address.setUser(user);
		assertEquals("Failed to add the user!", true, userDAO.addAddress(address));
		
	}
	
	
	*/
	
	/*  @Test
	public void testAddAddress() {
		user=userDAO.getByEmail("hr@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("203/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Bangolre");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		address.setUser(user);
		assertEquals("Failed to add the user!", true, userDAO.addAddress(address));
	
	}

	*/
	@Test
	public void testgetAddress() {
		user=userDAO.getByEmail("hr@gmail.com");
		assertEquals("Failed to add the get the list of address and not match the size !", 2, userDAO.listShippingAddress(user).size());
	
		assertEquals("Failed to add the get the list of address and not match the size !", "Mumbai", userDAO.getBillingAddress(user).getCity());
		
		
	}
		
	
}


