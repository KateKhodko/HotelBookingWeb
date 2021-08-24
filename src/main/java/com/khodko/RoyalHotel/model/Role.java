package com.khodko.RoyalHotel.model;

public class Role extends IdEntity {
			
	private String role;
	
	public Role() {
		super();
	}
	
	public Role(String role) {
		super();
		this.role = role;
	}
	
	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
		
}
