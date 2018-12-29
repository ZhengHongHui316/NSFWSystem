package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.exception.ActionException;
import cn.itcast.core.exception.ServiceException;
import cn.itcast.core.exception.SysException;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.role.service.RoleService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;
import freemarker.template.utility.StringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private User user;
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;

	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	private String[] userRoleIds;
	private String strName;
	
	// 列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		try {
			if (user != null) {
				if (StringUtils.isNotBlank(user.getName())) {
					user.setName(URLDecoder.decode(user.getName(), "utf-8"));
					queryHelper.addCondition("u.name like ?", "%" + user.getName() + "%");
				}

			}
			pageResult = userService.getPageResult(queryHelper, getPageNo(), getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";

	}

	// 跳转到新增页面
	public String addUI() {
		// 加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (user != null) {
				// 处理头像

				if (headImg != null) {
					// 1.保存头像到upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "")
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 复制文件
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					// 2.设置用户头像路径
					user.setHeadImg("user/" + fileName); // user/123.jpg
				}
				userService.saveUserAndRole(user, userRoleIds);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		// 加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		if (user != null && user.getId() != null) {
			strName =user.getName();
			user = userService.findObjectById(user.getId());
			// 处理角色回显
			List<UserRole> list = userService.getUserRolesByUserId(user.getId());
			if (list != null && list.size() > 0) {
				userRoleIds = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
				}
			}
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		if (user != null) {
			// 处理头像
			try {
				if (headImg != null) {
					// 1.保存头像到upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "")
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 复制文件
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					// 2.设置用户头像路径
					user.setHeadImg("user/" + fileName); // user/123.jpg
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			userService.updateUserAndRole(user, userRoleIds);
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (user != null && user.getId() != null) {
			userService.delete(user.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		return "list";
	}

	// 导出用户列表
	public void exportExcel() {
		try {
			// 1.查找用户列表
			// 2.导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userService.findObjects(), outputStream);
			if (outputStream != null) {
				outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 导入用户列表
	public String importExcel() {
		// 1.获取excel文件
		if (userExcel != null) {
			// 是否是excel
			if (userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				// 2.导入
				userService.importExcel(userExcel, userExcelFileName);
			}
		}
		return "list";
	}

	// 检验用户账号唯一
	public void verifyAccount() {
		try {
			// 1.获取账号
			if (user != null && StringUtils.isNotBlank(user.getAccount())) {
				// 2.根据账号到数据库中校验是否存在该账号对应的用户
				List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
				String strResult = "true";
				if (list != null && list.size() > 0) {
					// 说明该账号已经存在
					strResult = "false";
				}
				// 输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return this.headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return this.headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public File getUserExcel() {
		return this.userExcel;
	}

	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}

	public String getUserExcelContentType() {
		return this.userExcelContentType;
	}

	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}

	public String getUserExcelFileName() {
		return this.userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

	public String[] getUserRoleIds() {
		return this.userRoleIds;
	}

	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}
	
}
