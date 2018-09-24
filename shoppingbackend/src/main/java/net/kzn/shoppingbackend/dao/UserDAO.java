package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {
	
	//for adding user
	boolean addUser(User user);
	
	
	//user by its' mail
	
	User getByEmail(String email);
	
	//add the address
	
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	
	List<Address>listShippingAddress(User user);
	
	//add the cart
	boolean updateCart(Cart cart);

}
