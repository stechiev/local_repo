package ru.home.testapp.ws.json;

public class VoteInfo {
	
	private Integer userId;
	
	private Integer restarauntId;
	
	private String commentary;
	
	

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



	public String getCommentary() {
		return commentary;
	}



	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}


	public VoteInfo() {		
		// TODO Auto-generated constructor stub
	}



	public VoteInfo(Integer userId, Integer restarauntId) {
		super();
		this.userId = userId;
		this.restarauntId = restarauntId;
	}
	
	

}
