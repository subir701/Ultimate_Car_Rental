package com.masai.Service;

import java.util.List;

import com.masai.Entity.Transaction;
import com.masai.Exception.NoTransactionFoundException;
import com.masai.Exception.SomethingWentWrongException;

public interface TransactionService {
	
	List<Transaction> getListTranscation() throws SomethingWentWrongException,NoTransactionFoundException;
}
