package ru.home.testapp.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "daily_stat", catalog = "test", schema = "")
@NamedQueries({
	@NamedQuery(name = "DailyStat.findAll", 
			query = "SELECT d FROM DailyStat d") })
public class DailyStat {

	@Id
	@Column(name = "restaraunt_id")
	private Integer restarauntId;

	@Column(name = "vote_count")
	private Integer votesCount;

	public DailyStat() {
		// TODO Auto-generated constructor stub
	}

	public Integer getRestarauntId() {
		return restarauntId;
	}

	public void setRestarauntId(Integer restarauntId) {
		this.restarauntId = restarauntId;
	}

	public Integer getVotesCount() {
		return votesCount;
	}

	public void setVotesCount(Integer votesCount) {
		this.votesCount = votesCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restarauntId == null) ? 0 : restarauntId.hashCode());
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
		DailyStat other = (DailyStat) obj;
		if (restarauntId == null) {
			if (other.restarauntId != null)
				return false;
		} else if (!restarauntId.equals(other.restarauntId))
			return false;
		return true;
	}

}
