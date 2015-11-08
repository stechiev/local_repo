package ru.home.testapp.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.home.testapp.db.dao.UserDAOImpl;
import ru.home.testapp.db.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAOImpl userDAO;
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	/**Checks if user exists in the system
	 * @param userId user identifier in the system
	 * @return
	 * true - if user exists;
	 * <p>
	 * false - if not exists
	 */
	public boolean doExist(Integer userId){
		User user = userDAO.find(userId);
		return user == null ?  false :  true;
	}
	
	/**
	 * Checks if user has the admin role
	 * 
	 * @param userId user identifier in the system
	 * @return false - if user isn't in admin role 
	 * <p>
	 * true - if user has the admin role
	 */
	public boolean isUserAdmin(Integer userId){
		User user = userDAO.find(userId);
		if(user==null || user.getRoleId()!=User.ADMIN){
			return false;
		}
		return true;
	}

}
