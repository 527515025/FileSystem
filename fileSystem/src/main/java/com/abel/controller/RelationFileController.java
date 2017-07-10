package com.abel.controller;

import com.abel.bean.RelationFile;
import com.abel.bean.UploadFile;
import com.abel.service.RelationFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value="/relationFiles")
@Controller
public class RelationFileController {
	
	@Autowired
	private RelationFileService relationFileService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public List<RelationFile> list(HttpServletRequest request){
		return relationFileService.getByMap(null);
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
    public RelationFile detail(@PathVariable Integer id){
		return relationFileService.getById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
	@ResponseBody
    public RelationFile create(@RequestBody RelationFile relationFile){
		return relationFileService.create(relationFile);
    }

    @RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
    public RelationFile update(@RequestBody RelationFile relationFile){
		return relationFileService.update(relationFile);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public int delete(@PathVariable Integer id){
		return relationFileService.delete(id);
    }

	/**
	 * 文件关系确立.
	 * abel 测试用
	 * @param refId the ref id
	 * @param fileId the file id
	 * @return the response entity
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public Object submitFiles( Integer refId,Integer fileId) {
		List<UploadFile> uploadFiles=new ArrayList<>();
		for(int i=fileId+5;i>fileId;i--){
			UploadFile uploadFile=	new  UploadFile();
			uploadFile.setId(i);
			uploadFiles.add(uploadFile);
		}
		List<RelationFile> relationFiles = relationFileService.createRelation(refId, uploadFiles, "1");
		return relationFiles;
	}
}