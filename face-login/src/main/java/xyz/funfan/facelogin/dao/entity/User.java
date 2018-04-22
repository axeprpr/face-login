package xyz.funfan.facelogin.dao.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="t_users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8371034574707841692L;

	@Id
	private String uid;
	
	private String username;
	
	private String password;
	
	private String email;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
