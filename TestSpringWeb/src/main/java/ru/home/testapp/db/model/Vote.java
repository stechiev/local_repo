package ru.home.testapp.db.model;

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

@Entity
@Table(name = "vote", catalog = "test", schema = "")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Restaraunt restaraunt;
	
	@Column(name="is_fixed")
	private boolean isFixed;
	
	@Column
	@NotNull
	private Date created;
	
	@Column
	private Date updated;
	
	@Column
	private String descr;

	public Vote() {}
	
	

	public Vote(Integer id) {
		super();
		this.id = id;
	}
	
	

	public Vote(User user, Restaraunt restaraunt, boolean isFixed, Date created) {
		super();
		this.user = user;
		this.restaraunt = restaraunt;
		this.isFixed = isFixed;
		this.created = created;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaraunt getRestaraunt() {
		return restaraunt;
	}

	public void setRestaraunt(Restaraunt restaraunt) {
		this.restaraunt = restaraunt;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
