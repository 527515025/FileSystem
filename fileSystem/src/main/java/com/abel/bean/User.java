package com.abel.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

public class User {
	private Integer id;
	@NotBlank(message = "{user.cnname.notBlank}")
	private String cnname;
	private String username;
	@JsonIgnore
	private String password;
	private String rePassword;
	private String historyPassword;
	private String email;
	@JsonIgnore
	private String telephone;
	private String mobilePhone;
	private String wechatId;
	@JsonIgnore
	private String skill;
	private Integer departmentId;
	@JsonIgnore
	private Integer loginCount;
	private Integer avatarId;

	private Integer roleId;
	private String wechatOpenid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getHistoryPassword() {
		return historyPassword;
	}

	public void setHistoryPassword(String historyPassword) {
		this.historyPassword = historyPassword;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public String getWehcatOpenid() {
		return wechatOpenid;
	}

	public void setWehcatOpenid(String wehcatOpenid) {
		this.wechatOpenid = wehcatOpenid;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", cnname=" + cnname +
			", username=" + username +
			", password=" + password +
			", email=" + email +
			", telephone=" + telephone +
			", mobilePhone=" + mobilePhone +
			", wechatId=" + wechatId +
			", skill=" + skill +
			", departmentId=" + departmentId +
			", loginCount=" + loginCount +
			'}';
		}
}