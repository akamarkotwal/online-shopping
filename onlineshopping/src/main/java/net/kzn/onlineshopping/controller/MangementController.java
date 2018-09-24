package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.util.FileUploadUtility;
import net.kzn.onlineshopping.validatior.ProductValidator;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class MangementController {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	
	private static final Logger logger=LoggerFactory.getLogger(MangementController.class);
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showMangeProduct(@RequestParam(name="operation",required=false)String opertion) {
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickedMangedProduct",true);
		mv.addObject("title","Mange Product");
		Product nProduct=new Product();
		//set fileld
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		if(opertion!=null) {
		if(opertion.equals("product")) {
			mv.addObject("message","product submited sucessfully");
			
		}else if(opertion.equals("category")) {
			mv.addObject("message","category submited sucessfully");
			
		}
		}
		return mv;
		
	}
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id ) {
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickedMangedProduct",true);
		mv.addObject("title","Mange Product");
		//fetch the product from database
		Product nProduct=productDAO.get(id);
		//set the product fetch from database
		mv.addObject("product",nProduct);
		
		
		return mv;
		
		
	}
	
	
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmisson(@Valid @ModelAttribute("product") Product mProduct,BindingResult result,Model model,HttpServletRequest request) {
		new ProductValidator().validate(mProduct,result);
		//handler image validation for new product
		if(mProduct.getId()==0) {
		new ProductValidator().validate(mProduct, result);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}
		
		
		//check if threre are any error
		if(result.hasErrors()) {
		model.addAttribute("userClickedMangedProduct",true);
		model.addAttribute("title","Mange Product");
		model.addAttribute("message","Validation failed for product Submission");
		return "page";
		}
		
		
		logger.info(mProduct.toString());
		
		
		if(mProduct.getId()==0) {
			//create new product if the id is 0
			productDAO.add(mProduct);
		}
		
		else{
			//update the product if the id is not 0
			productDAO.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
	
		
		return  "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		//is going to fetch product from database
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		//activation and deactivation of active field
		product.setActive(!product.isActive());
		//update the product
		productDAO.update(product);
		
		return(isActive)?"you have sucessfully deactivated the product with id"+product.getId()
		:"you have sucessfully activated the product with id"+product.getId();
	}
	
	//to handle category submission
	@RequestMapping(value="category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		//add the new Category
		categoryDAO.add(category);
		return  "redirect:/manage/products?operation=category";
		
	}
	
	//returing categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
		
		
	}
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
	
}
