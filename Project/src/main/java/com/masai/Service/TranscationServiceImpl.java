package com.masai.Service;

import java.util.List;

import com.masai.DAO.TransactionDAO;
import com.masai.DAO.TransactionDAOImpl;
import com.masai.Entity.Transaction;
import com.masai.Exception.NoTransactionFoundException;
import com.masai.Exception.SomethingWentWrongException;

public class TranscationServiceImpl implements TransactionService {

	

	@Override
	public List<Transaction> getListTranscation() throws SomethingWentWrongException, NoTransactionFoundException {
		TransactionDAO dao = new TransactionDAOImpl();
		
		return dao.getListTranscation();
	}

}
