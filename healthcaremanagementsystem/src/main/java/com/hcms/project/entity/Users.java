//package com.hcms.project.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.InheritanceType;
//import jakarta.validation.constraints.NotNull;
//
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public class Users {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long userId;
//	@NotNull
//	@Column(unique = true)
//	private String username;
//	@NotNull
//	private String password;
//		
//	
//	public Users() {
//		super();
//	}
//
//
//	public Users(Long userId, @NotNull String username, @NotNull String password) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.password = password;
//	}
//
//
//	public Long getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//
//	public String getUsername() {
//		return username;
//	}
//
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + "]";
//	}
//}
//	