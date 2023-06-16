package com.masai.DAO;

import java.time.LocalDate;
import java.util.List;

import com.masai.Entity.Avaliable;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.User;
import com.masai.Exception.NoCustomerFoundException;
import com.masai.Exception.UnableToAddCustomerException;
import com.masai.Exception.UnableToFetchCustomerException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public void addCustomer(User user) throws UnableToAddCustomerException {
		EntityManager em=EMUtils.getConnection();
		EntityTransaction et=null;
		try {
			
			et=em.getTransaction();
			Query query=em.createQuery("SELECT count(c) FROM User c WHERE username = : username");
			query.setParameter("username", user.getUsername());
			if((long)query.getSingleResult() > 0) {
				throw new UnableToAddCustomerException("The Username :"+ user.getUsername()+" is present.");
			}
			et.begin();
			em.persist(user);
			et.commit();
		}catch(PersistenceException ex) {
			throw new UnableToAddCustomerException("Unable to process request , try again later");
		}finally {
			em.close();
		}

	}

	@Override
	public void loginCustomer(String username, String password) throws UnableToFetchCustomerException {
		EntityManager em=null;
		try {
			em=EMUtils.getConnection();
			Query query= em.createQuery("SELECT c.id FROM User c WHERE username = : username AND password = : password AND is_deleted LIKE NO");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<Integer> listint = (List<Integer>)query.getResultList();
			if(listint.size() == 0) {
				throw new UnableToFetchCustomerException("The username or password is incorrect.");
			}
			LoggedInUserId.loggedInUserId = listint.get(0);
		}catch(PersistenceException ex) {
			throw new UnableToFetchCustomerException("Unable to process request , try again later");
		}finally {
			em.close();
		}

	}

	@Override
	public void changePassword(String oldPassword, String newPassword)
			throws NoCustomerFoundException, UnableToFetchCustomerException {
		EntityManager em = null;
		try {
			em = EMUtils.getConnection();
			Query query = em.createQuery("SELECT count(c) FROM User c WHERE password = :oldPassword AND id = :id");
			query.setParameter("oldPassword", oldPassword);
			query.setParameter("id", LoggedInUserId.loggedInUserId);
			Long userCount = (Long)query.getSingleResult();
			if(userCount == 0) {
				//you are here old password is incorrect for logged in user
				throw new NoCustomerFoundException("Invalid old password");
			}
			//You are here means all checks done, We can proceed for changing the password
			User customer = em.find(User.class, LoggedInUserId.loggedInUserId);
			EntityTransaction et = em.getTransaction();
			et.begin();
			customer.setPassword(newPassword);
			et.commit();
		}catch(PersistenceException ex) {
			throw new UnableToFetchCustomerException("Unable to process request, try again later");
		}finally{
			em.close();
		}

	}

	@Override
	public void deleteCustomer() throws UnableToFetchCustomerException {
		EntityManager em = null;
		try {
			em = EMUtils.getConnection();
			User customer = em.find(User.class, LoggedInUserId.loggedInUserId);
			EntityTransaction et = em.getTransaction();
			et.begin();
			customer.setIs_deleted(Avaliable.YES);
			et.commit();
		}catch(PersistenceException ex) {
			throw new UnableToFetchCustomerException("Unable to process request, try again later");
		}finally{
			em.close();
		}

	}

	@Override
	public List<Object[]> getListOfCustomer() throws NoCustomerFoundException, UnableToFetchCustomerException {
		EntityManager em = null;
		List<Object[]> customerList = null;
		try {
			em = EMUtils.getConnection();
			Query query = em.createQuery("SELECT c.name, c.username, c.dob, c.age, c.phoneNumber, c.address "
					+ "c.is_deleted FROM User c");
			customerList = query.getResultList();
			if(customerList.size() == 0)
				throw new NoCustomerFoundException("No Customer Found");
		}catch(PersistenceException ex) {
			throw new UnableToFetchCustomerException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		return customerList;
	}

		public static void main(String[] args) {
			User user= new User("Sumit", 23, "DPW3200", "LIC943", 1234567890, "Indore", LocalDate.parse("1999-01-01"), "SumitGupta", "1234", Avaliable.NO, null,null,null);
			CustomerDAO dao= new CustomerDAOImpl();
			try {
				dao.addCustomer(user);
			} catch (UnableToAddCustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
