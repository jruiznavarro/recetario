package com.jruiznavarro.apps.recetario.user.entity;

/**
 * UsuarioEntityRequest.java
 * 
 * @author Javier Ruiznavarro Ambrona
 *
 */
public class GetUserEntityRequest {

	private String alias;
	private String pass;

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
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
