package ru.home.testapp.db.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "restaraunt", catalog = "test", schema = "")
public class Restaraunt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Size(min = 1, max = 255)
	@NotNull
	private String address;
	@Column
	@Size(min = 1, max = 20)
	private String phone;
	@Column
	@Size(min = 1, max = 255)
	@NotNull
	private String name;
	@Column
	@NotNull
	@JsonIgnore
	private Date created;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaraunt", fetch = FetchType.EAGER)
	private List<Dish> dishes;

	public Restaraunt() {
	}

	public Restaraunt(int id) {
		super();
		this.id = id;
	}

	public Restaraunt(int id, String address, String phone, String name, Date created) {
		super();
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.name = name;
		this.created = created;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaraunt other = (Restaraunt) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
