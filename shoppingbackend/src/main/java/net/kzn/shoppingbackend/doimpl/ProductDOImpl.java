package net.kzn.shoppingbackend.doimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// single product fetching
	public Product get(int productId) {

		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
   //to add product
	public boolean add(Product product) {
		try {
			// add the category to the datbase table
			sessionFactory.getCurrentSession().persist(product);

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}

		
		
		
	}
    //Update prodct
	public boolean update(Product product) {
		try {
			// update the category to the datbase table
			sessionFactory.getCurrentSession().update(product);

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}
  //delete product
	public boolean delete(Product product) {
		
		
			try {
				product.setActive(false);
				return this.update(product);

			} catch (Exception ex) {
				ex.printStackTrace();
				return false;

			}
		}


	public List<Product> listActiveProduct() {
		String selectActiveProduct="FROM Product WHERE active=:active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProduct,Product.class).setParameter("active",true).getResultList();
	}

	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory, Product.class)
						.setParameter("active", true)
						.setParameter("categoryId",categoryId)
							.getResultList();
	}

	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();	
	}

	// List
	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product" , Product.class).getResultList();
				
									
	}

}
