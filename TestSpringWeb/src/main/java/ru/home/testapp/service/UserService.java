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
	
	public boolean doExist(Integer userId){
		User user = userDAO.find(userId);
		return user == null ?  false :  true;
	}
	
	public boolean isUserAdmin(Integer userId){
		User user = userDAO.find(userId);
		if(user==null || user.getRoleId()!=User.ADMIN){
			return false;
		}
		return true;
	}

}
