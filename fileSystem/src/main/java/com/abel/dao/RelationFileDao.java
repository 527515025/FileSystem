package com.abel.dao;

import com.abel.bean.RelationFile;

import java.util.List;
import java.util.Map;

public interface RelationFileDao {

	public List<RelationFile> getByMap(Map<String, Object> map);
	public RelationFile getById(Integer id);
	public Integer create(RelationFile relationFile);
	public int update(RelationFile relationFile);
	public int delete(Integer id);

	public void batchCreateRelation(List<RelationFile> relationFiles);

}