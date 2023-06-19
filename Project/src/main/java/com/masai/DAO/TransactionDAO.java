package com.masai.DAO;

import java.util.List;

import com.masai.Entity.Transaction;
import com.masai.Exception.NoTransactionFoundException;
import com.masai.Exception.SomethingWentWrongException;

public interface TransactionDAO {
	void addTransaction(Transaction transcation,int reservation_id,String model)throws SomethingWentWrongException;
	List<Transaction> getListTranscation() throws SomethingWentWrongException,NoTransactionFoundException;
}
