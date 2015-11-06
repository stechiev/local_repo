package ru.home.testapp.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.home.testapp.db.model.Dish;
@Repository
public class DishDAOImpl extends AbstractDAO<Dish>{
	
	@PersistenceContext(name="tutorialPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DishDAOImpl() {
		super();
		entityClass = Dish.class;
	}
	
	public DishDAOImpl(Class<Dish> entityClass) {
		super(entityClass);
	}
	
	public void remove(List<Dish> dishList){
		for(Dish dish : dishList){
			em.remove(dish);
		}
	}

}
