package cn.itcast.core.permission;

import cn.itcast.nsfw.user.entity.User;

public interface PermissionCheck {
	/**
	 * 判断用户是否有code对应的权限
	 * @param user 用户
	 * @param string 子系统的权限标识符
	 * @return true or false
	 */
	public boolean isAccessible(User user, String code);
		
		
	
}
