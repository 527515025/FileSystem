package com.abel.service;

import com.abel.bean.RelationFile;
import com.abel.bean.UploadFile;
import com.abel.dao.UploadFileDao;
import com.abel.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;



@Service
public class UploadFileService {

    @Autowired
    private UploadFileDao uploadFileDao;

    /**
     * The env.
     */
    @Autowired
    Environment env;

    public List<UploadFile> getByMap(Map<String, Object> map) {
        return uploadFileDao.getByMap(map);
    }

    public UploadFile getById(Integer id) {
        return uploadFileDao.getById(id);
    }

    public Integer create(UploadFile uploadFile) {
        return uploadFileDao.create(uploadFile);
    }

    public Integer update(UploadFile uploadFile) {
        return uploadFileDao.update(uploadFile);
    }

    public Integer delete(Integer id) {
        return uploadFileDao.delete(id);
    }

    /**
     * 删除文件
     *
     * @param relationId
     */
    @Transactional
    public void deleteFile(Integer fileId, Integer relationId, String flag) {
        RelationFile relationFiles = new RelationFile();
        if (fileId != null) {
            relationFiles.setFileId(fileId);
        }
        if (relationId != null) {
            relationFiles.setRelationId(relationId);
        }
        if (flag != null) {
            relationFiles.setFlag(flag);
        }
        deleteFileAndRelation(relationFiles);
    }

    /**
     * 删除文件和中间关系.
     *
     * @param relationFiles
     */
    @Transactional
    public void deleteFileAndRelation(RelationFile relationFiles) {
        List<UploadFile> uploadFiles = uploadFileDao.getUploadFileList(relationFiles);
        uploadFileDao.deleteFile(relationFiles);
        uploadFileDao.deleteRelation(relationFiles);
        if (uploadFiles != null && uploadFiles.size() > 0) {
            for (UploadFile uploadFile : uploadFiles) {
                if (uploadFile != null)
                    FileUtil.deleteUploadFile(env.getProperty("attachment.root.path") + uploadFile.getFilePath());
            }
        }
    }

    /**
     * Download the attachment
     *
     * @param request
     * @param response
     * @param fileId
     * @return
     */
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, Integer fileId)
            throws Exception {
        List<UploadFile> files = getFileList(null, fileId, null);
        // 判断业务逻辑
        // 然后下载
        //目前只支持单文件下载
        // ...
        if (files != null && !files.isEmpty()) {
            FileUtil.downloadFile(response, files.get(0), env.getProperty("attachment.root.path"));
        }
    }

    /**
     * Get the attachment list
     *
     * @param relationId
     * @param fileId
     * @param flag
     * @return the List<Attachment>
     */
    public List<UploadFile> getFileList(Integer relationId, Integer fileId, String flag) throws Exception {
        RelationFile relationFiles = new RelationFile();
        if (fileId != null || relationId != null) {
            if (fileId != null) {
                // 通过serviceid 查询 文件
                relationFiles.setFileId(fileId);
            } else {
                // 通过fileid 查询 文件
                relationFiles.setRelationId(relationId);
                relationFiles.setFlag(flag);
            }
        } else {
            throw new Exception("文件不存在");
        }
        return uploadFileDao.getUploadFileList(relationFiles);

    }

    public boolean getPictureById(HttpServletResponse response, Integer imageId) throws Exception {
        UploadFile file = uploadFileDao.getById(imageId);
        if (null != file) {
            String path = env.getProperty("attachment.root.path") + file.getFilePath();
            return FileUtil.getPhoto(response, path);
        } else {
            //抛出异常
            throw  new Exception();
        }
    }

    /**
     * Get the picture by imagePath. (通过文件path 获取头像)
     *
     * @param response
     * @param
     * @return
     */
    public boolean getPictureByPath(HttpServletResponse response, String imagePath) throws Exception {
        String path = env.getProperty("attachment.root.path") + imagePath;
        return FileUtil.getPhoto(response, path);
    }

    /**
     * 删除图像文件
     *
     * @param imageId
     * @return the boolean 文件是否被删除
     */
    public boolean deletePictureById(Integer imageId) throws Exception {
        UploadFile file = uploadFileDao.getById(imageId);
        if (null != file) {
            String path = env.getProperty("attachment.root.path") + file.getFilePath();
            boolean flag = FileUtil.deleteUploadFile(path);
            return flag;
        } else {
            //抛出异常
            throw  new Exception();        }
    }

}
