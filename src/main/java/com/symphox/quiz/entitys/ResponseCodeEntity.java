package com.symphox.quiz.entitys;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 共用回傳格式
 * 
 * @author Lucifers
 *
 */
@ApiModel(description = "回應物件實體")
public class ResponseCodeEntity {
	public static final Integer SUCCESS = 1;
	public static final Integer FAILURE = 0;
	public static final String DEFAULT_ERROR_CODE = "00000";

	@ApiModelProperty(value = "請求狀態 - 0: 失敗；1: 成功", example="1")
	private Integer status;

	@ApiModelProperty(value = "錯誤碼", example = "00000")
	private String errorCode;

	@ApiModelProperty(value = "回應資料", example = "{\n" + "\"account\":\"test\"}")
	private Object datas;

	/**
	 * 成功的回傳
	 * 
	 * @param data 回傳資料
	 */
	public ResponseCodeEntity() {
		this.status = SUCCESS;
		this.errorCode = DEFAULT_ERROR_CODE;
		this.setDatas("");
	}

	/**
	 * 成功的回傳
	 * 
	 * @param data 回傳資料
	 */
	public ResponseCodeEntity(Object data) {
		this.status = SUCCESS;
		this.errorCode = DEFAULT_ERROR_CODE;
		this.setDatas(data);
	}

	/**
	 * ResponseErrorCodeEntity錯誤的回傳
	 * 
	 * @param responseErrorCode 錯誤碼對照表
	 */
	public ResponseCodeEntity(ResponseErrorCodeEntity responseErrorCode) {
		this.status = FAILURE;
		this.errorCode = responseErrorCode.getCode().toString();
		this.setDatas(responseErrorCode.getDesc());
	}

	/**
	 * 字串錯誤的回傳
	 * 
	 * @param msg
	 */
	public ResponseCodeEntity(Exception ex) {
		// 組合錯誤訊息
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String errorMsg = ResponseErrorCodeEntity.SYSTEM_ERROR.getDesc() + ": " + sw.toString().split("\r\n")[0];

		this.status = FAILURE;
		this.errorCode = ResponseErrorCodeEntity.SYSTEM_ERROR.getCode().toString();
		this.setDatas(errorMsg);
	}

	/* Getter and Setter Methods */

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
}
