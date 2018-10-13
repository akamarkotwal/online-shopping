package net.kzn.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {
	
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {	
		if(session.getAttribute("userModel")==null) {
			// get the authentication object
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  
		 User  user = userDAO.getByEmail(authentication.getName());
		 
		 if(user!=null) {
				// create a new model
				userModel = new UserModel();
				// set the name and the id
				userModel.setId(user.getId());
				userModel.setFullname(user.getFirstName() + " " + user.getLastName());
				userModel.setRole(user.getRole());
				
				if(user.getRole().equals("USER")) {
					userModel.setCart(user.getCart());					
				}				
		  
				session.setAttribute("userModel", userModel);
				return userModel;
			
		}
		


		}
	return (UserModel) session.getAttribute("userModel");	
	
	
 }

}