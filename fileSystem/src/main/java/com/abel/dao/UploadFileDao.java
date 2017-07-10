package com.abel.dao;

import com.abel.bean.RelationFile;
import com.abel.bean.UploadFile;

import java.util.List;
import java.util.Map;


public interface UploadFileDao {
	
	public List<UploadFile> getByMap(Map<String, Object> map);
	
	public UploadFile getById(Integer id);

	public Integer create(UploadFile uploadFile);
	
	public Integer update(UploadFile uploadFile);
	
	public Integer delete(Integer id);

	/**
	 * 通过relationFiles 查询UploadFile集合
	 * select UploadFile by fileId and relationId
	 *
	 * @param relationFiles
	 * @return the List<UploadFile>
	 */
	public List<UploadFile> getUploadFileList(RelationFile relationFiles);

	/**
	 * 删除文件.
	 *
	 * @param relationFiles
	 * @return Integer
	 */
	public Integer deleteFile(RelationFile relationFiles);

	/**
	 * 删除关系.
	 *
	 * @param relationFiles
	 * @return the list
	 */
	public Integer deleteRelation(RelationFile relationFiles);
}
