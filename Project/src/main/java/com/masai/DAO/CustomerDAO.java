package com.masai.DAO;

import java.util.List;

import com.masai.Entity.User;
import com.masai.Exception.NoCustomerFoundException;
import com.masai.Exception.UnableToAddCustomerException;
import com.masai.Exception.UnableToFetchCustomerException;

public interface CustomerDAO {
	void addCustomer(User user)throws UnableToAddCustomerException;
	void loginCustomer(String username,String password)throws UnableToFetchCustomerException;
	void changePassword(String oldPassword, String newPassword)throws NoCustomerFoundException,UnableToFetchCustomerException;
	void deleteCustomer()throws UnableToFetchCustomerException;
	List<Object[]> getListOfCustomer()throws NoCustomerFoundException,UnableToFetchCustomerException;
}
