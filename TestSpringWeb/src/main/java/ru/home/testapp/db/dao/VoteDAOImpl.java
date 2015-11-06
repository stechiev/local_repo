package ru.home.testapp.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.Vote;

@Repository
public class VoteDAOImpl extends AbstractDAO<Vote> {
	
	@PersistenceContext(name="tutorialPU")
	private EntityManager em;

	public VoteDAOImpl() {
		super();
		entityClass = Vote.class;
	}

	public VoteDAOImpl(Class<Vote> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
