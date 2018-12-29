package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

import cn.itcast.nsfw.role.entity.Role;

public class UserRoleId implements Serializable {
	private Role role;
	private String userId;
	
	public UserRoleId(Role role, String userId) {
		super();
		this.role = role;
		this.userId = userId;
	}
	public UserRoleId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
		result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
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
		UserRoleId other = (UserRoleId) obj;
		if (this.role == null) {
			if (other.role != null)
				return false;
		} else if (!this.role.equals(other.role))
			return false;
		if (this.userId == null) {
			if (other.userId != null)
				return false;
		} else if (!this.userId.equals(other.userId))
			return false;
		return true;
	}
	

}
