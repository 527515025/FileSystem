package com.abel.util;

import com.abel.bean.UploadFile;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * 文件上传和下载工具类
 * @since 2017-05-23
 * @version 1.0
 * */
public class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);

	/**
	 * 删除文件.
	 * abel
	 * @param path            被删除文件路径
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean deleteUploadFile(String path) {
		boolean flag = false;
		File file = new File(path);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			flag =file.delete();
		}
		return flag;
	}
	
	
	/**
	 * 返回页面图片预览.
	 *
	 * @param response the response
	 * @param path the path
	 * @return file exists true / file not exists false
	 * @throws Exception the exception
	 */
	public static boolean getPhoto(HttpServletResponse response,String path) throws Exception {
		// 读取文件内容
		File file = new File(path);
		// 路径为文件且不为空则取图片文件
		if (file.isFile() && file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			long size = file.length();
			byte[] data = new byte[(int) size];
			fis.read(data, 0, (int) size);
			fis.close();
			
			// 将二进制流 写入response的outputstream
			response.setContentType("image/png");
			OutputStream out = response.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 文件上传.
	 *
	 * @param mr the mr
	 * @param filePath the file path
	 * @return the UploadFile
	 * @throws FileUploadException the file upload exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 */
	public static UploadFile uploadFile(MultipartHttpServletRequest mr, String filePath)
			throws FileUploadException, IOException, FileNotFoundException {
		UploadFile uploadFile = null;

		// 文件存放的目录
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 获得file类型的input的name
		Iterator<String> nameList = mr.getFileNames();
		String fileName = nameList.next();
		// 获得到文件

		MultipartFile fm = mr.getFile(fileName);
		double Size = fm.getSize();

		String fileSize = Size + "B";
		if (Size / (1024 * 1024) >= 0.01) {
			fileSize = (double) (Math.round(Size / (1024 * 1024) * 100) / 100.0) + "MB";
		} else if (Size / 1024 >= 0.01) {
			fileSize = (double) (Math.round(Size / 1024 * 100) / 100.0) + "KB";
		}

		// 获得原始文件名字
		String oldFileName = fm.getOriginalFilename();
		// 文件的后缀名
		String filetype ="null";
		if(oldFileName.contains(".")){
			filetype = oldFileName.substring(oldFileName.lastIndexOf('.'));
		}
		String newFileName = UUIDBuild.getUUID() + filetype;
		String realPath = filePath + newFileName;
		BufferedInputStream in = new BufferedInputStream(fm.getInputStream());
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File(realPath)));
		Streams.copy(in, bout, true);
		in.close();
		bout.close();
		
		uploadFile = new UploadFile();
		uploadFile.setFileName(oldFileName);
		uploadFile.setFilePath(newFileName);
		uploadFile.setFileType(filetype);
		uploadFile.setFlag(1);
		uploadFile.setFileSize(fileSize);
		uploadFile.setUploadTime(new Date());

		return uploadFile;
	}

	/**
	 * 文件下载 abel.
	 *
	 * @param response the response
	 * @param uploadFile            需要下载的文件
	 * @param filePath the file path
	 */
	public static void downloadFile(HttpServletResponse response, UploadFile uploadFile, String filePath) {
		try {
			filePath = filePath + uploadFile.getFilePath();
			File file = new File(filePath);
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-disposition",
					"attachment;filename=\"" + new String(uploadFile.getFileName().getBytes("gb2312"), "ISO8859-1"));

			response.addHeader("Content-Length", "" + file.length());

			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		} catch (IOException ex) {
			log.error("下载文件异常[" + ex.getMessage() + "]");
		}
	}

	/**
	 * 将获取到的文件流转换成文件暂存.
	 *
	 * @param ins the ins
	 * @param file the file
	 */
	public static  void inputstreamtofile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
