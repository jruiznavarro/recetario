package com.jruiznavarro.apps.recetario.common.user.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
	
	@NotNull
	private String alias;
	@NotNull
	private String name;
	@NotNull
	private String surname;
	@NotNull
	private String email;
	@NotNull
	private String pass;
	@NotNull
	private Date birthdayDate;
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

}
