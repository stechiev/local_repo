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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author stech_000 <p>Question entity supposed to identify voting by days; With
 *         the beginning of the each day, with new votes System creates a new
 *         question in database and associates all user votes to that question.
 *         Next day, with a first new user vote system would close previous
 *         Question instance and open a new one. This behaviour can help with
 *         further extending system functionality, like configuring new types of
 *         voting by menu or restaurants, by time periods etc. furthermore it
 *         helps to properly identify user vote to exclude double voting.
 */
@Entity
@Table(name = "question", catalog = "test", schema = "")
@NamedQueries({
		@NamedQuery(name = "Question.findActiveSinceDate", query = "SELECT q FROM Question q WHERE q.isActive = :isActive AND q.created >= :beginDate"),
		@NamedQuery(name = "Question.UpdateActive", query = "UPDATE Question q SET q.isActive = :isActive, q.updated = :updated") })
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Size(max = 255)
	private String name;

	@Column(name = "active")
	private boolean isActive;

	@Column(updatable = false)
	@NotNull
	private Date created;

	@Column
	private Date updated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
	private List<Vote> votes;

	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(String name, boolean isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", isActive=" + isActive + ", created=" + created
				+ ", updated=" + updated + ", votes=" + votes + "]";
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
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
