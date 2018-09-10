package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		context= new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
		
	}

	/*@Test
	public void testAddCategory() {
		category=new Category();
		category.setName("television");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_1.png");
		
		assertEquals("sucessfully added a category inside table",true,categoryDAO.add(category));
		
	}*/
	/*@Test
	public void testGetCategory() {
		category=categoryDAO.get(3);
		
		assertEquals("sucessfully fetched a category inside table","washing",category.getName());
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		category=categoryDAO.get(3);
		category.setName("dishwasher");
		
		assertEquals("sucessfully update a category in the table",true,categoryDAO.update(category));
	}*/
	/*@Test
	public void testDeleteCategory() {
		category=categoryDAO.get(3);
		
		
		assertEquals("sucessfully delete a category in the table",true,categoryDAO.delete(category));
	}*/
	
	/*@Test
	public void testListOfCategory() {
		category=categoryDAO.get(3);
		
		
		assertEquals("sucessfully fetch list of category from the table",3,categoryDAO.list().size());
	}*/
	
	@Test
	public void testCRUDCategory(){
		//add opereation
		
		category=new Category();
		category.setName("laptop");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_1.png");
		
		assertEquals("sucessfully added a category inside table",true,categoryDAO.add(category));
		
		
		category=new Category();
		category.setName("television");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_2.png");
		
		assertEquals("sucessfully added a category inside table",true,categoryDAO.add(category));
		
		//fetching and updating category
		category=categoryDAO.get(2);
		category.setName("tv");
		
		assertEquals("sucessfully update a category in the table",true,categoryDAO.update(category));
		
		//delete the category	
		assertEquals("sucessfully delete a category in the table",true,categoryDAO.delete(category));
		
		//list all category
		assertEquals("sucessfully fetch list of category from the table",1,categoryDAO.list().size());
	}
}
