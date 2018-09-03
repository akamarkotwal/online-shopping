package net.kzn.shoppingbackend.doimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDOImpl implements CategoryDAO {
	
	private static List<Category> categories = new ArrayList<>();
	
	static {
		
		Category category=new Category();
		
		/*adding first catogary*/
		category.setId(1);
		category.setName("television");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_1.png");
		categories.add(category);

		/*adding second catogary*/
		category=new Category();
		category.setId(2);
		category.setName("mobile");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_2.png");
		categories.add(category);
		
		/*adding first catogary*/
		category=new Category();
		category.setId(3);
		category.setName("laptop");
		category.setDescrption("old ids gold");
		category.setImageURL("CAT_3.png");
		categories.add(category);

		
	}

	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		for(Category category:categories) {
			if(category.getId()==id) return category;
			
		}
		return null;
	}

}
