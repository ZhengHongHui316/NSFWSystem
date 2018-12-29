package cn.itcast.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.bcel.Const;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;


public class LoginAction extends ActionSupport{
	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	//跳转到登录界面 
	public String toLoginUI(){
		return "loginUI";
	}
	//登录
	public String login(){
		if(user !=null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNoneBlank(user.getPassword())){
				//根据用户的账号和密码查询用户列表	
				List<User> list = userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
				if(list !=null && list.size() >0){//登录成功
					//2.1、登录成功
					User user = list.get(0);
					//2.1.1、根据用户id查询该用户的所有角色
					user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
					//2.1.2、将用户信息保存到session中
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,user);
					//2.1.3、将用户登录记录到日志文件
					Log log=LogFactory.getLog(getClass()); 
					log.info("用户名称为:" + user.getName() + "的用户登录了系统");
					//2.1.4、重定向跳转到首页
					return "home";
				}else{//登录失败
					
					loginResult="账号或密码不正确!";
				}
			}
			else{
				loginResult="账号或密码不能为空!";
			}
		}else{
			loginResult="请输入账号和密码!";
		}
		return toLoginUI();
	}
	//退出，注销
	public String logout(){
		//清楚session中保存的用户信息
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	//跳转到没有权限页面
	public String toNoPermissionUI(){
		return "noPermissionUI"; 
	 }
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoginResult() {
		return this.loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
}
