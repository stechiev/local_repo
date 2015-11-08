package ru.home.testapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.home.testapp.db.dao.RestarauntDAOImpl;
import ru.home.testapp.db.dao.UserDAOImpl;
import ru.home.testapp.db.dao.VoteDAOImpl;
import ru.home.testapp.db.model.DailyStat;
import ru.home.testapp.db.model.Question;
import ru.home.testapp.db.model.Restaraunt;
import ru.home.testapp.db.model.User;
import ru.home.testapp.db.model.Vote;
import ru.home.testapp.exception.VoteSystemException;
import ru.home.testapp.ws.json.VoteInfo;

@Service
public class VoteService {
	
	@Autowired
	private VoteDAOImpl voteDAO;
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private RestarauntDAOImpl restarauntDAO;

	public VoteService() {
		// TODO Auto-generated constructor stub
	}
	@Transactional(rollbackFor=Exception.class)
	public void addVote(VoteInfo vInfo, Question question) throws VoteSystemException{
		
		User user = userDAO.find(vInfo.getUserId());		
		if(user==null){
			throw new VoteSystemException(400, "not found user by userId =" + vInfo.getUserId());
		}
		
		Restaraunt restaraunt = restarauntDAO.find(vInfo.getRestarauntId());
		if(restaraunt==null){
			throw new VoteSystemException(400, "not found restaraunt by restarauntId =" + vInfo.getRestarauntId());
		}
		
		Vote vote = voteDAO.findByUserAndQuestion(user, question);
		
		if (vote == null) {
			vote = new Vote();
			vote.setCreated(new Date());
		}else{
			vote.setUpdated(new Date());
		}
		vote.setUser(user);
		vote.setRestaraunt(restaraunt);
		vote.setQuestion(question);
		voteDAO.create(vote);
	}
	
	public List<DailyStat> getDailyStat(){
		return voteDAO.getDailyStat();
	}

}
