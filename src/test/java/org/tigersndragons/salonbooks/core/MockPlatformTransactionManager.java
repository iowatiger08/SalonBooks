package org.tigersndragons.salonbooks.core;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

public class MockPlatformTransactionManager implements
		PlatformTransactionManager {

	public TransactionStatus getTransaction(TransactionDefinition definition)
			throws TransactionException {
		return new SimpleTransactionStatus();
	}

	public void commit(TransactionStatus status) throws TransactionException {
		

	}

	public void rollback(TransactionStatus status) throws TransactionException {
		

	}

}
