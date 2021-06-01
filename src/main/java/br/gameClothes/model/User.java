/**
 * @author marcony.souza
 */
package br.gameClothes.model;

import java.util.List;

/**
 * @author marcony.souza
 *
 */
public class User {

	private Integer idUser;
	
	private String username;
	
	private String password;
	
	private List<Product> myStore;
		
	public List<Product> getMyStore() {
		return myStore;
	}

	public void setMyStore(List<Product> myStore) {
		this.myStore = myStore;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", myStore=" + myStore
				+ ", getMyStore()=" + getMyStore() + ", getIdUser()=" + getIdUser() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
