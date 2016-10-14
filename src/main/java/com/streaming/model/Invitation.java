package com.streaming.model;

import java.io.Serializable;

public class Invitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2794098044242089894L;
	int id;
	String mail_from ;
	String mail_to;
	boolean accepted ;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMail_from() {
		return mail_from;
	}


	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}


	public String getMail_to() {
		return mail_to;
	}


	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}


	public boolean isAccepted() {
		return accepted;
	}


	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}


	public Invitation() {
		// TODO Auto-generated constructor stub
	}

}
