package ru.home.testapp.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.Restaraunt;
@Repository
public class RestarauntDAOImpl extends AbstractDAO<Restaraunt> {
	
	@PersistenceContext(name="tutorialPU")
	private EntityManager em;

	public RestarauntDAOImpl() {
		super();
		entityClass=Restaraunt.class;
	}

	public RestarauntDAOImpl(Class<Restaraunt> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
