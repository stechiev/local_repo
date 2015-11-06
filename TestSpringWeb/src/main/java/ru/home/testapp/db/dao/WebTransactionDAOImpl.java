package ru.home.testapp.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.WebTransaction;

@Repository
public class WebTransactionDAOImpl extends AbstractDAO<WebTransaction>{
	
	public WebTransactionDAOImpl(Class<WebTransaction> entityClass) {
		super(entityClass);
	}
	
	public WebTransactionDAOImpl() {
		super();
		entityClass = WebTransaction.class;
	}



	@PersistenceContext(name="tutorialPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
