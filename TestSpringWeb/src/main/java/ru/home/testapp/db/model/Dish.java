package ru.home.testapp.db.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dish", catalog = "test", schema = "")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Size(max = 60)
	@Column
	private String name;
	@JoinColumn(name = "restaraunt_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonIgnore
	private Restaraunt restaraunt;

	@Column
	private boolean isActive;

	@Column
	private BigDecimal price;
	@Column(updatable= false)
	@NotNull
	@JsonIgnore
	private Date created;
	@Column
	@JsonIgnore
	private Date updated;

	public Dish() {
	}

	public Dish(int id) {
		super();
		this.id = id;
	}

	public Dish(int id, String name, Date created) {
		super();
		this.id = id;
		this.name = name;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restaraunt getRestaraunt() {
		return restaraunt;
	}

	public void setRestaraunt(Restaraunt restaraunt) {
		this.restaraunt = restaraunt;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
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
		Dish other = (Dish) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
