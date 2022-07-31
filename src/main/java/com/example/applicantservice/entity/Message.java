package com.example.applicantservice.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="ip_address")
	private String ipAdress;
	
	@Column(name="message")
	private String message;
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("\"email\":\"").append(email).append('\"');
		sb.append(", \"ipAdress\":\"").append(ipAdress).append('\"');
		sb.append(", \"message\":\"").append(message).append('\"');
		sb.append('}');
		return sb.toString();
	}
	
	public Message(String email, String ipAdress, String message) {
		this.email = email;
		this.ipAdress = ipAdress;
		this.message = message;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIpAdress() {
		return ipAdress;
	}
	
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
