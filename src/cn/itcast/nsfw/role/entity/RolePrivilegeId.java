package cn.itcast.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {
	private Role role;
	private String code;
	
	public RolePrivilegeId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RolePrivilegeId(Role role, String code) {
		super();
		this.role = role;
		this.code = code;
	}
	public Role getRole() {
		return this.role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
		result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
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
		RolePrivilegeId other = (RolePrivilegeId) obj;
		if (this.code == null) {
			if (other.code != null)
				return false;
		} else if (!this.code.equals(other.code))
			return false;
		if (this.role == null) {
			if (other.role != null)
				return false;
		} else if (!this.role.equals(other.role))
			return false;
		return true;
	}
	
	
	
}
