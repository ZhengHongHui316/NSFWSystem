package cn.itcast.nsfw.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User implements Serializable {
	private String id;
	private String dept;
	private String account;
	private String name;
	private String password;
	
	private String headImg;//保存头像图片相对路径
	private boolean gender;
	private String state;
	private String mobile;
	private String email;
	private Date birthday;
	private String memo;//备注
	private List<UserRole> userRoles;
	//用户状态
	public static String USER_STATE_VALID="1";//有效
	public static String USER_STATE_INVALID="0";//无效
	public User() {
	}
	public User(String id, String dept, String account, String name, String password, String headImg, boolean gender,
			String state, String mobile, String email, Date birthday, String memo) {
		super();
		this.id = id;
		this.dept = dept;
		this.account = account;
		this.name = name;
		this.password = password;
		this.headImg = headImg;
		this.gender = gender;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return this.dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAccount() {
		return this.account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return this.headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public boolean isGender() {
		return this.gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return this.birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return this.memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
}
