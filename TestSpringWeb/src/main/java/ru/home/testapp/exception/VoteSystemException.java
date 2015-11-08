package ru.home.testapp.exception;

public class VoteSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int httpCode;

	private String descr;

	public VoteSystemException() {
		super();
	}

	public VoteSystemException(int httpCode, String descr) {
		super();
		this.httpCode = httpCode;
		this.descr = descr;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
