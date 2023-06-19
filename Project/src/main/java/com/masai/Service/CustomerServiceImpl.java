package com.masai.Service;

import java.util.List;

import com.masai.DAO.CustomerDAO;
import com.masai.DAO.CustomerDAOImpl;
import com.masai.Entity.User;
import com.masai.Exception.NoCustomerFoundException;
import com.masai.Exception.UnableToAddCustomerException;
import com.masai.Exception.UnableToFetchCustomerException;



public class CustomerServiceImpl implements CustomerService {

	@Override
	public void addCustomer(User user) throws UnableToAddCustomerException {
		CustomerDAO dao= new CustomerDAOImpl();
		
		dao.addCustomer(user);
		
	}

	@Override
	public void loginCustomer(String username, String password) throws UnableToFetchCustomerException {
		CustomerDAO dao= new CustomerDAOImpl();
		
		dao.loginCustomer(username, password);
		

	}

	@Override
	public void changePassword(String oldPassword, String newPassword)
			throws NoCustomerFoundException, UnableToFetchCustomerException {
		CustomerDAO dao= new CustomerDAOImpl();
		
		dao.changePassword(oldPassword, newPassword);
		

	}

	@Override
	public void deleteCustomer() throws UnableToFetchCustomerException {
		CustomerDAO dao= new CustomerDAOImpl();
		try {
		dao.deleteCustomer();
		}catch(UnableToFetchCustomerException ex) {
			System.out.println(ex.getMessage());
		}

	}

	@Override
	public List<Object[]> getListOfCustomer() throws NoCustomerFoundException, UnableToFetchCustomerException {
		CustomerDAO dao= new CustomerDAOImpl();
		
		return dao.getListOfCustomer();
	}

}
