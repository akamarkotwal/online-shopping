package net.kzn.onlineshopping.model;

import java.io.Serializable;

import net.kzn.shoppingbackend.dto.Cart;

public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String fullname;
	private String email;
	private String role;
	private Cart cart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", fullname=" + fullname + ", email=" + email + ", role=" + role + ", cart="
				+ cart + "]";
	}

	
}
