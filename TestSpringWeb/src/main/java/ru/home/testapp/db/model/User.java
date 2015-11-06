package ru.home.testapp.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user", catalog = "test", schema = "")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1, max = 255)
	@NotNull
	@Column
	private String name;
	
	@Size(min = 1, max = 45)
	@NotNull
	@Column
	private String login;
	
	@Size(min = 1, max = 45)
	@NotNull
	@Column
	private String password;
	
	@Column(name="role_id")
	@NotNull
	private Integer roleId;
	
	@Column
	@NotNull
	@JsonIgnore
	private Date created;
	
	@Column
	@JsonIgnore
	private Date updated;
	
	public static final Integer ADMIN = 1;
	public static final Integer USER = 3;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String name, String login, String password, Integer roleId) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
