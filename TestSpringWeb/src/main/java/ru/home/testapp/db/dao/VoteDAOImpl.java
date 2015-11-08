package ru.home.testapp.db.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.home.testapp.db.model.DailyStat;
import ru.home.testapp.db.model.Question;
import ru.home.testapp.db.model.Restaraunt;
import ru.home.testapp.db.model.User;
import ru.home.testapp.db.model.Vote;
import ru.home.testapp.util.DateUtils;
import ru.home.testapp.ws.json.VoteInfo;

@Repository
public class VoteDAOImpl extends AbstractDAO<Vote> {

	@PersistenceContext(name = "tutorialPU")
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

	/*@Transactional
	public void addVote(VoteInfo vInfo) {

		User user = em.find(User.class, vInfo.getUserId());
		Restaraunt restaraunt = em.find(Restaraunt.class, vInfo.getRestarauntId());

		Vote vote = findByUserAndQuestion(user, restaraunt);
		if (vote == null) {
			vote = new Vote();
		}
		vote.setUser(user);
		vote.setRestaraunt(restaraunt);
		vote.setCreated(new Date());
		super.create(vote);
	}*/

	public Vote findByUserAndQuestion(User user, Question question) {
		try {
			return em.createNamedQuery("Vote.findByUserIdAndQuestion", Vote.class).setParameter("user", user)
					.setParameter("question", question).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}

	}
	
	public List<DailyStat> getDailyStat(){
		
		return em.createNamedQuery("DailyStat.findAll", DailyStat.class).getResultList();
	}

}
