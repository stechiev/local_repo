package ru.home.testapp.service;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.home.testapp.db.dao.QuestionDAOImpl;
import ru.home.testapp.db.model.Question;
import ru.home.testapp.util.DateUtils;

@Service
public class QuestionService {

	@Autowired
	private QuestionDAOImpl questionDAO;

	private Logger log = Logger.getLogger(this.getClass().getName());

	public QuestionService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * method to get active Question from database for the current day. 
	 * It searches for active Question in database <b>for current day beginning from 00:00</b> . 
	 * <p>
	 * If there is no active question in question table, 
	 * then method disables all previous question instances, creates new active Question, 
	 * persists it and then returns persited Question object
	 * <p>
	 * If there is an active Question instance for given period of time then method simply returns this instance
	 * 
	 * @return returns current active Question object
	 */
	@Transactional
	public Question getCurrentQuestion() {
		log.info("Getting instance of active question");
		Date beginDate = DateUtils.getCurrentDateWithoutTime();
		Question question = questionDAO.findActiveSinceDate(beginDate);

		if (question == null) {
			log.info("Not found active question; disabling previous questions...");
			int disabledCount = questionDAO.disableAllQuestions();
			log.info("Number of disabled questions is: " + disabledCount);
			log.info("Creating new active question...");
			question = new Question();
			question.setName("Restaurant Voting");
			question.setActive(true);
			question.setCreated(new Date());
			questionDAO.create(question);
			log.info("Created new active question : " + question.toString());

		}
		return question;

	}

}
