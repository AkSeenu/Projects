package com.testpro.w2ssolution.entity;

import java.sql.Date;

import com.testpro.w2ssolution.common.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "User_Pro")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer id;
	
	@Column(nullable = false,length = 50,unique = true)
	private String name;
	
	@Column(nullable = false,length = 50)
	private String emailId;
	
	@Column(nullable = false,length = 10)
	private String number;
	
	@Column(nullable = false,length = 50)
	private String password;
	
	
	private Boolean isActive=false;
	
	@Column(nullable = true)
	private Integer loginCount;
	
	
	private Date createdDate;
	
	private String type = RoleType.NORMAL.toString();
	
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@PrePersist
	public void onSave() {
		
		if(createdDate == null)
			this.createdDate = new Date(System.currentTimeMillis());
					
	}
	
	@Override
	public String toString() {
		return "User : " + name + " Is Successfuly SignUp With him/her EMail-Id : " + emailId; 
	}
	
}
