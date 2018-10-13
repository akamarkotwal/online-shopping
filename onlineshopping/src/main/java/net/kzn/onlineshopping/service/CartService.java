package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//return the cart  of the user who is logged in
	private Cart getCart() {
		
			return ((UserModel)session.getAttribute("userModel")).getCart();
	}

	
	//return entire cart line 
	public List<CartLine> getCartLines() {
          
          return cartLineDAO.list(this.getCart().getId());
	
}


	public String manageCartLine(int cartLineId, int count) {
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine==null) {
			
			return "result=error";
		}
		else {
			
			Product product=cartLine.getProduct();
			
			
			double oldTotal=cartLine.getTotal();
			
			
			//checking if the product is avilable
			if(product.getQuantity()< count) {
				return "result=unavailable";
			}
		
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice()* count);
			cartLineDAO.update(cartLine);
			
			Cart cart=this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal()- oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			return "result=updated";
			
		}
		
	}


	public String deleteCartLine(int cartLineId) {
	  CartLine cartline= cartLineDAO.get(cartLineId);
	  if(cartline==null) {
		  return "result=error";
		  
	  }else {
		  //update the cart
		  Cart cart=this.getCart();
		  cart.setGrandTotal(cart.getGrandTotal()- cartline.getTotal());
		  cart.setCartLines(cart.getCartLines() -1);
		  cartLineDAO.updateCart(cart);
	  //remove the cartline
		  cartLineDAO.remove(cartline);
		  
		  return "result=deleted";
		  
		  
	  
	  }
	  
	  
	  
	}


	public String addCartLine(int productId) {
		String responce=null;
		
		Cart cart=this.getCart();
		
		CartLine cartLine=cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine==null) {
			//add the new cartline
			cartLine=new CartLine();
			
			//fetch the product
			Product product=productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setProductCount(1);
			
			cartLine.setTotal(product.getUnitPrice());
			
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			
			cartLineDAO.updateCart(cart);
			
			
			responce="result=added";
			
		}
		
		else {
			
			//check carline has reached maximum level
			if(cartLine.getProductCount()<3) {
				//update the product count for that cartline
				
				responce=this.manageCartLine(cartLine.getId(),cartLine.getProductCount() + 1);
				
			}
			else {
				responce="result=maximum";
			}
		}
		
		
		
		
		
		
		return responce;
	}

}