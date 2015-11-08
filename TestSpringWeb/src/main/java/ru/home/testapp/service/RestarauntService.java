package ru.home.testapp.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.home.testapp.db.dao.DishDAOImpl;
import ru.home.testapp.db.dao.RestarauntDAOImpl;
import ru.home.testapp.db.model.Dish;
import ru.home.testapp.db.model.Restaraunt;
import ru.home.testapp.exception.VoteSystemException;
import ru.home.testapp.ws.json.DishInfo;

@Service
public class RestarauntService {
	
	@Autowired
	private RestarauntDAOImpl restarauntDAO;
	@Autowired
	private DishDAOImpl dishDAO;
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	public RestarauntService() {
		// TODO Auto-generated constructor stub
	}
	/** adds a restaurant in the system database
	 * @param restaraunt Restaurant object to add
	 * @return Restaurant object with setted Id property available by restaurant.getId() method
	 * @throws VoteSystemException 
	 */
	@Transactional
	public Restaraunt addRestaraunt(Restaraunt restaraunt) throws VoteSystemException{
		log.info("Restaraunt to add is:" + restaraunt);
		if(restaraunt.getName()==null || restaraunt.getAddress()==null){
			throw new VoteSystemException(400, "restaurant fields 'name', 'address' cannot be null");
		}
		restaraunt.setCreated(new Date());
		if(restaraunt.getDishes()!=null){
			for(Dish dish : restaraunt.getDishes()){
				dish.setRestaraunt(restaraunt);
				dish.setCreated(restaraunt.getCreated());
			}
		}
    	restarauntDAO.create(restaraunt);
    	log.info("restaraunt added; id = " + restaraunt.getId());
    	return restaraunt;
	}
	
	/**
	 * @return List of all restaurants in the system
	 */
	public List<Restaraunt> getRestaraunts(){
		log.info("Restaraunts to get...");
    	List<Restaraunt> restaraunts = restarauntDAO.findAll();
    	log.info("Number of found restaraunts is:" +restaraunts.size());
    	return restaraunts;
	}
	
	/**
	 * @param restarauntId restaurant id in the system
	 * @param dishList list of DishInfo objects
	 * @return Restaurant object with added Dish list available by restaurant.getDishes() method
	 * @throws VoteSystemException if restaurant not found in database
	 */
	@Transactional(rollbackFor=Exception.class)
	public Restaraunt addLunchInfo(Integer restarauntId, List<DishInfo> dishList) throws VoteSystemException{
		log.info("Adding dishes to restaraunt with id = " + restarauntId);
		
		Restaraunt restaraunt = restarauntDAO.find(restarauntId);
		if(restaraunt==null){
			throw new VoteSystemException(400, "not found restaraunt by restarauntId =" + restarauntId);
		}
		//List<Dish> oldDishes = restaraunt.getDishes();
		
		//dishDAO.remove(restaraunt.getDishes());
		restaraunt.getDishes().clear();
		
		for(DishInfo dInfo : dishList){
			Dish dish = new Dish();
			dish.setName(dInfo.getName());
			dish.setPrice(new BigDecimal(dInfo.getPrice()));
			dish.setCreated(new Date());
			dish.setRestaraunt(restaraunt);
			restaraunt.getDishes().add(dish);
		}
		restarauntDAO.edit(restaraunt);
		return restaraunt;
	}
	
	/*public void removeRestaraunt(Integer rId){
		log.info("Getting restaraunt to remove by id = " + rId);
		Restaraunt restaraunt = restarauntDAO.find(rId);
    	log.info("Found!; removing...");
    	restarauntDAO.remove(restaraunt);
	}*/

}
