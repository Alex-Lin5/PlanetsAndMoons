package com.revature.models;

public class User {

	private int id;
	private String username;
	private String password;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
//	@Override
//	public String toString(){
//		String info = "";
//		if(this == null) return null;
//		if(this.id>-1)
//			info += String.format("Id=%d ", this.id);
//		if(this.username != null & this.password != null)
//			info += String.format("username=%s, password=%s. \n", this.username, this.password);
//		return this+"\n"+info;
//	}
}
