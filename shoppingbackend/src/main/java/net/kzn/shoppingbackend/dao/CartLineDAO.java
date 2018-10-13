package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	// list of available cartLine
		public List<CartLine> listAvailable(int cartId);
		
		// fetch the CartLine based on cartId and productId
		public CartLine getByCartAndProduct(int cartId, int productId);
		
		// updating the cart
		boolean updateCart(Cart cart);
}
