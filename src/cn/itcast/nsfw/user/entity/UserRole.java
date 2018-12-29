package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

public class UserRole implements Serializable{
	private UserRoleId id;
	
	public UserRole() {
	
	}

	public UserRole(UserRoleId id) {
		this.id = id;
	}

	public UserRoleId getId() {
		return this.id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}
	
}
