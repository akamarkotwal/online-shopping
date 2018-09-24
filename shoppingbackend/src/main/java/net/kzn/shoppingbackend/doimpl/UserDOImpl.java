package net.kzn.shoppingbackend.doimpl;



import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public boolean addUser(User user) {
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		}

	public boolean addAddress(Address address) {
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateCart(Cart cart) {
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery="FROM User WHERE email=:email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,User.class).setParameter("email", email).getSingleResult();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery="FROM Address WHERE user=:user AND billing=:billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class).setParameter("user",user).setParameter("billing", true).getSingleResult();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery="FROM Address WHERE user=:user AND shipping=:shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class).setParameter("user",user).setParameter("shipping", true).getResultList();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
