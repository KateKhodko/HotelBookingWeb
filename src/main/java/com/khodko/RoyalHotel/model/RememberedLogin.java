package com.khodko.RoyalHotel.model;

import java.sql.Timestamp;

public class RememberedLogin implements DbEntity {
	
	private String tokenHash;
	private Integer userId;
	private Timestamp expiereAt;
	
	public RememberedLogin() {
		
	}
	
	public RememberedLogin(String tokenHash, Integer userId, Timestamp expiereAt) {
		this.tokenHash = tokenHash;
		this.userId = userId;
		this.expiereAt = expiereAt;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getExpiereAt() {
		return expiereAt;
	}

	public void setExpiereAt(Timestamp expiereAt) {
		this.expiereAt = expiereAt;
	}		

	@Override
	public String toString() {
		return "RememberdLogin [tokenHash=" + tokenHash + ", user_id=" + userId + ", expiereAt=" + expiereAt + "]";
	}		

}
