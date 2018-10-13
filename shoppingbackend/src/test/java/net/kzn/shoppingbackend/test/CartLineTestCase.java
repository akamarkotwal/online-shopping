package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

public class CartLineTestCase {
	
private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private Product product;
	private User user;
	private Cart cart;
	private CartLine cartLine = null;
	
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddCartLine() {
		// fetch the user and then cart of that user
				 user = userDAO.getByEmail("donbosco@gmail.com");		
				
				//fetch the cart of user
				 cart = user.getCart();
				 product = productDAO.get(4);
				
				// Create a new CartLine
				cartLine = new CartLine();
				cartLine.setBuyingPrice(product.getUnitPrice());
				cartLine.setProductCount(cartLine.getProductCount() + 1);
				
			
				
				 cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				 
				 cartLine.setAvailable(true);
				 
				 cartLine.setCartId(cart.getId());
				 
				 cartLine.setProduct(product);
				 
				 assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
				 
				 //update the cart
				 
				 cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
				 
				 cart.setCartLines(cart.getCartLines() + 1);
				 assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart)); 
				 
	}

}
