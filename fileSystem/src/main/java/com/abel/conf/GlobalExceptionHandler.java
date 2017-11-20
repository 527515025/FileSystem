package com.abel.conf;

import com.abel.constant.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GlobalExceptionHandler.
 *
 * @author abel
 * @version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Handle max upload size exceeded exception.
	 * 文件上传大小异常
	 * @param ex the ex
	 * @param request the request
	 * @param response the response
	 * @return the error info
	 */
	@ExceptionHandler(MultipartException.class)
	public  void handleMaxUploadSizeExceededException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		ErrorMessage error = ErrorMessage.UPLOAD_FILE_MAX_ERROR;
		pouplateExceptionResponse(response, 500, error.toString());
	}

	/**
	 * Pouplate exception response.
	 *
	 * @param response the response
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	private void pouplateExceptionResponse(HttpServletResponse response, int errorCode, String errorMessage) {
		try {
			response.sendError(errorCode, errorMessage);
		} catch (IOException e) {
			logger.error("failed to populate response error", e);
		}
	}

}
