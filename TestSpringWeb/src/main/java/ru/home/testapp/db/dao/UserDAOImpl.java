package ru.home.testapp.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.User;

@Repository
public class UserDAOImpl extends AbstractDAO<User> {
	
	@PersistenceContext(name="tutorialPU")
	private EntityManager em;

	public UserDAOImpl() {
		super();
		entityClass=User.class;
	}

	public UserDAOImpl(Class<User> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
