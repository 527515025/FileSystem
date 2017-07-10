package com.abel.controller;

import com.abel.bean.UploadFile;
import com.abel.service.UploadFileService;
import com.abel.util.CommonUtil;
import com.abel.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@RequestMapping(value="/files")
@RestController
public class UploadFileController {
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	Environment env;
	
	/**
	 * 上传图片文件.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/images",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object filesUploadImage(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileType=CommonUtil.getFileType(request);
		if(fileType.equals(".png")||fileType.equals(".jpg")||fileType.equals(".jpeg")){
			UploadFile uploadFile = FileUtil.uploadFile(request,env.getProperty("attachment.root.path"));
			uploadFileService.create(uploadFile);
			return uploadFile;
		}else{
			throw new Exception();
		}
	}
	
	/**
	 * 获取图片文件
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/images/{imageId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void getPicture(HttpServletResponse response,
			@PathVariable  Integer imageId) throws Exception {
		uploadFileService.getPictureById(response, imageId);
	}

	/**
	 * 上传文件.
	 * abel
	 * @param request the request
	 * @param response the response
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object filesUpload(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadFile uploadFile = FileUtil.uploadFile(request,env.getProperty("attachment.root.path"));
		return  uploadFileService.create(uploadFile);
	}

	/**
	 * 删除文件.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteFile(@PathVariable Integer id) {
		uploadFileService.deleteFile(id, null,null);
		return null;
	}

	/**
	 * 下载文件.
	 *
	 * @param request the request
	 * @param response the response
	 * @param fileId the file id
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/{fileId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
								   @PathVariable Integer fileId) throws Exception {
		uploadFileService.downloadFile(request, response, fileId);
	}
}
