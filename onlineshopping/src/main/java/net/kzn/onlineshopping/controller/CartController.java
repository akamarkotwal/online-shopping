package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		ModelAndView mv=new ModelAndView("page");
		
		if(result!=null) {
			switch(result) {
				case "updated":
					mv.addObject("message", "Cartline has been sucessfully update");
					break;
					
				case "added":
					mv.addObject("message", "Cartline has been sucessfully added");
					break;
				
				case "maximum":
					mv.addObject("message", "Cartline has been maximum count ");
					break;
			
					
				case "deleted":
					mv.addObject("message", "Cartline has been sucessfully deleted");
					break;
			
				case "unavailable":
					mv.addObject("message", "Product quntity is unaviliable");
					break;
				
					
				case "error":
					mv.addObject("message", "something error is happend");
					break;
		
		
			}
		}
		
		
		mv.addObject("title", "Shopping Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		
		return mv;

	
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String udpateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);		
		return "redirect:/cart/show?"+response;
		
		
	}
	

	@RequestMapping("/{cartLineId}/remove")
	public String removeCartLine(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);		
		return "redirect:/cart/show?"+response;
		
		
	}
	@RequestMapping("/add/{productId}/product")
	public String addCartLine(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);		
		return "redirect:/cart/show?"+response;
		
		
	}
	
	
}
