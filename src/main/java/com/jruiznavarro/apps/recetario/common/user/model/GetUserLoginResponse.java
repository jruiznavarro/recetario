package com.jruiznavarro.apps.recetario.common.user.model;

import java.util.Date;

import com.jruiznavarro.apps.recetario.common.user.entity.Rol;

/**
 * Usuario.java
 * 
 * @author Javier Ruiznavarro Ambrona
 *
 */
public class GetUserLoginResponse {

	private String alias;
	private String name;
	private String surname;
	private Date endDate;
	private boolean userActivation;
	private Rol rol;
	private String email;

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the userActivation
	 */
	public boolean isUserActivation() {
		return userActivation;
	}

	/**
	 * @param userActivation
	 *            the userActivation to set
	 */
	public void setUserActivation(boolean userActivation) {
		this.userActivation = userActivation;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
