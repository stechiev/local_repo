package ru.home.testapp.ws.json;

public class DishInfo{
	private String name;
	
	private String price;
	
	public DishInfo(){}

	public DishInfo(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DishInfo [name=" + name + ", price=" + price + "]";
	}
	
	

}
