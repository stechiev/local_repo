package ru.home.testapp.ws.json;

import java.util.List;

import ru.home.testapp.db.model.Restaraunt;

public class RestarauntInfo {

	private Integer userId;

	private Integer restarauntId;

	private Restaraunt restaraunt;

	private List<DishInfo> dishes;

	public RestarauntInfo() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRestarauntId() {
		return restarauntId;
	}

	public void setRestarauntId(Integer restarauntId) {
		this.restarauntId = restarauntId;
	}

	public Restaraunt getRestaraunt() {
		return restaraunt;
	}

	public void setRestaraunt(Restaraunt restaraunt) {
		this.restaraunt = restaraunt;
	}

	public List<DishInfo> getDishes() {
		return dishes;
	}

	public void setDishes(List<DishInfo> dishes) {
		this.dishes = dishes;
	}

	@Override
	public String toString() {
		return "RestarauntInfo [restarauntId=" + restarauntId + ", dishes=" + dishes.toString() + "]";
	}

}
