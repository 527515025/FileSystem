package com.abel.constant;

/**
 * 异常处理集合常量字符串
 * Created by yangyibo on 17/2/17.
 */
public enum ErrorMessage {
    INTERNAL_SERVER_UNKNOWN_ERROR("服务器异常,请联系管理员或稍后重试。"),
    UPLOAD_FILE_MAX_ERROR("上传文件过大，请上传10M以下的文件");

    private String message;

    private ErrorMessage(String message){
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
