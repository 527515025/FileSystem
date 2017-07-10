package com.abel.bean;

import java.util.Date;


public class RelationFile {
	private Integer id;
	private Integer relationId;
	private Integer fileId;
	private Date createTime;
	private String flag; //1. 评论附件

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "RelationFile{" +
			"id=" + id +
			", relationId=" + relationId +
			", fileId=" + fileId +
			", createTime=" + createTime +
			", flag=" + flag +
			'}';
		}
}