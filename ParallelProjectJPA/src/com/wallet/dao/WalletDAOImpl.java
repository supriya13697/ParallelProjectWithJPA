package com.wallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.wallet.dto.Customer;
import com.wallet.dto.PrintTransactions;
import com.wallet.exception.WalletException;
import com.wallet.exception.InvalidPhoneNumber;

public class WalletDAOImpl implements WalletDAO {

	EntityManager em;

	public WalletDAOImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
		em = emf.createEntityManager();
	}

	@Override
	public Customer createAccount(Customer c) throws WalletException {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		return c;
	}

	@Override
	public Customer getAccount(String mobileno) {
		em.getTransaction().begin();
		Customer cShow = em.find(Customer.class, mobileno);
		em.getTransaction().commit();
		return cShow;
	}

	@Override
	public boolean setAccount(String mobileNo, double amount) {
		em.getTransaction().begin();
		Customer cShow = em.find(Customer.class, mobileNo);
		cShow.setAmount(amount);
		em.merge(cShow);
		em.getTransaction().commit();
		return true;
	}

	
	
	@Override
	public List<PrintTransactions> getTransactions(String mobileNo)
			throws WalletException, InvalidPhoneNumber {
		
		List<PrintTransactions> tranList = null;
		em.getTransaction().begin();
		Query query = em.createQuery("From PrintTransactions where mobileNumber = :mobNo");
		query.setParameter("mobNo", mobileNo);
		tranList = query.getResultList();
		if(tranList == null)
			throw new WalletException("No transactions are made yet");
		em.getTransaction().commit();
		return tranList;
	}

	@Override
	public void loadTransaction(PrintTransactions pt) {
		
		em.getTransaction().begin();
		em.persist(pt);
		em.getTransaction().commit();
	}

}
