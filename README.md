# FileSystem
此模块分为两个部分。

* 文件上传、下载、读取 UploadFile 模块
* 文件与其他对象的关联关系维护 RelationFile 模块。

逻辑如下：
调用上传文件API 时，上传文件，并存储文件信息到 数据库，返回文件信息给前端。

当用户点击保存时，上传对象信息和文件信息集合，保存文件和对象的关系。

例如：添加评论 ，评论过中包含多个附件

* 在编写评论后添加附件，（添加附件则直接上传附件，并存储附件信息到数据库）。
* 当编写评论完成点击提交评论时，此时新建评论信息，并新建评论和附件的关联中间表信息。

requestBody 如下：
```
 {
        "type": "1",
        "relationType": "1",
        "relationId": "1",
        "description": "这是一个包含多个附件的评论",
        "parentId": null,
        "upCount": 15,
        "downCount": 3,
        "isSolution": 1,
        "createBy": "admin",
        "createTime": 1499312583000,
        "uploadFiles":[
        	{"id":"52"},
			{"id":"53"},
    		{"id":"54"},
    		{"id":"55"}]
    }
```
在评论的创建方法中的逻辑如下：
```
public Comment create(Comment comment) {
        commentDao.create(comment);
        if (null != comment.getUploadFiles() && comment.getUploadFiles().size() > 0) {
            ////判断附件不为空创建关联关系。
            relationFileService.createRelation(comment.getId(), comment.getUploadFiles(), "1");
        }
        return comment;
    }
```
