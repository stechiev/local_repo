package ru.home.testapp.db.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.Question;

@Repository
public class QuestionDAOImpl extends AbstractDAO<Question> {

	@PersistenceContext(name="tutorialPU")
	private EntityManager em;
	
	public QuestionDAOImpl() {
		super();
		entityClass=Question.class;
		// TODO Auto-generated constructor stub
	}

	public QuestionDAOImpl(Class<Question> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public Question findActiveSinceDate(Date date){
		try{
			return em.createNamedQuery("Question.findActiveSinceDate", Question.class)
					.setParameter("isActive", true)
					.setParameter("beginDate", date).getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	public int disableAllQuestions(){		
		int count = em.createNamedQuery("Question.UpdateActive").setParameter("isActive", false)
				.setParameter("updated", new Date())
		.executeUpdate();
		return count;
	}

}
