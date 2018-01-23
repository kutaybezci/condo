package com.kutaybezci.condo.dal.model;

public class UserPrivilegeEntity {
	private long userPrivilegeId;
	private long userId;
	private String privilege;

	public long getUserPrivilegeId() {
		return userPrivilegeId;
	}

	public void setUserPrivilegeId(long userPrivilegeId) {
		this.userPrivilegeId = userPrivilegeId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
