package cn.itcast.nsfw.home.action;

import cn.itcast.core.action.BaseAction;

public class HomeAction extends BaseAction {
	//跳转到纳税访问系统首页
	public String frame(){
		return "frame";
	}
	//跳转到纳税访问系统首页-顶部
	public String top(){
		return "top";
	}
	//跳转到纳税访问系统首页-左边菜单
	public String left(){
		return "left";
	}
}
