package com.symphox.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.entitys.ResponseErrorCodeEntity;

/**
 * 回覆統一的錯誤格式
 * 
 * @author Lucifers
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 資料庫相關錯誤
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DatabaseErrorsExceptions.class)
	public ResponseEntity<?> databaseErrorsExceptions(DatabaseErrorsExceptions ex) {
		ResponseCodeEntity responseCodeEntity;
		try {
			ResponseErrorCodeEntity responseErrorCodeEntity = ResponseErrorCodeEntity.of(ex.getMessage());
			responseCodeEntity = new ResponseCodeEntity(responseErrorCodeEntity);
		} catch (Exception e) {
			responseCodeEntity = new ResponseCodeEntity(e);
		}
//		_setEveryRunningFlagFalse();
		return new ResponseEntity<>(responseCodeEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 一般系統錯誤
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex) {
		ResponseCodeEntity responseCodeEntity = new ResponseCodeEntity(ex);
		return new ResponseEntity<>(responseCodeEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
