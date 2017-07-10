package com.abel.service;

import com.abel.bean.RelationFile;
import com.abel.bean.UploadFile;
import com.abel.dao.RelationFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RelationFileService {
    @Autowired
	private RelationFileDao relationFileDao;
	
	public List<RelationFile> getByMap(Map<String,Object> map){
	    return relationFileDao.getByMap(map);
	}
	
	public RelationFile getById(Integer id){
		return relationFileDao.getById(id);
	}
	
	public RelationFile create(RelationFile relationFile){
		relationFileDao.create(relationFile);
		return relationFile;
	}
	
	public RelationFile update(RelationFile relationFile){
		relationFileDao.update(relationFile);
		return relationFile;
	}
	
	public int delete(Integer id){
		return relationFileDao.delete(id);
	}

	/**
	 * Create the relation
	 *
	 * @param relationId
	 * @param uploadFile
	 * @param flag
	 * @return the List<AttachmentRelation>
	 */
	@Transactional
	public List<RelationFile> createRelation(Integer relationId, List<UploadFile> uploadFile, String flag) {
		List<RelationFile> relationFiles = new ArrayList<>();
		if (relationId != null && uploadFile != null && uploadFile.size() > 0) {
			for (int i = 0; i < uploadFile.size(); i++) {
				RelationFile relationFile = new RelationFile();
				relationFile.setRelationId(relationId);
				relationFile.setFileId(uploadFile.get(i).getId());
				relationFile.setCreateTime(new Date());
				relationFile.setFlag(flag);
				relationFiles.add(relationFile);
			}
			relationFileDao.batchCreateRelation(relationFiles);
		}
		return relationFiles;
	}
    
}