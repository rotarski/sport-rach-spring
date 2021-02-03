package com.sport.rach.auth.utils.resetpassword.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.sport.rach.user.model.User;

@Entity
@Table(name="auth_password_reset_token")
public class PasswordResetToken implements Serializable{

	private static final long serialVersionUID = 2037371439589458122L;

	private static final int EXPIRATION = 1000 *60 *60; // one hour for click link
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String token;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name="user_id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	public PasswordResetToken() {
	}

	public PasswordResetToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	@PrePersist
	public void prePersist() {
		this.expiryDate = new Date(new Date().getTime()+ EXPIRATION) ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
