package net.kzn.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.kzn.onlineshopping.model.RegisterModel;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Component
public class RegisterHandler {
	
@Autowired
private UserDAO userDAO;

	
	public RegisterModel init(){
		
		
		
		return new RegisterModel();
		
	}
public void addUser(RegisterModel registerModel,User user) {
	registerModel.setUser(user);
	
}
public void addBilling(RegisterModel registerModel,Address billing) {
	registerModel.setBilling(billing);
}
public String saveAll(RegisterModel model) {
	String transitionValue="success";
	//fetch the user
	
	User user=model.getUser();
	 if(user.getRole().equals("USER")) {
		 
		 Cart cart=new Cart();
		 cart.setUser(user);
		 user.setCart(cart);
	 }
	
	 
	 //save the user
	 userDAO.addUser(user);
	 
	 
	 //get the address
	 
	 Address billing=model.getBilling();
	 
	 billing.setUser(user);
	 
	 billing.setBilling(true);
	 
	userDAO.addAddress(billing);
	 
	return transitionValue;
	
	
}
public String validateUser(User user, MessageContext error) {
	String transitionValue="success";
	
	//checking password match
	if(!(user.getPassword().equals(user.getConfirmPassword()))) {
		error.addMessage(new MessageBuilder()
				.error()
				.source("ConfirmPassword")
				.defaultText("password does not match")
				.build());
		
		transitionValue="failure";
	}
	
	//checking the uniquness of email id
	 if(userDAO.getByEmail(user.getEmail())!=null) {
		 error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("email alredy exit")
					.build());
		 
		 
		 transitionValue="failure";
		 
	 }
	
	return transitionValue;
	
	
}

}


