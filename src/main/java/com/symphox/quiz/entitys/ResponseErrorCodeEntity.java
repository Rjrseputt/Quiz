package com.symphox.quiz.entitys;

/**
 * 錯誤碼對照表
 * 
 * @author Lucifers
 *
 */
public enum ResponseErrorCodeEntity {
	/**
	 * 成功訊息
	 */
	REQUEST_SUCCESS(00000, "請求成功"),

	/**
	 * 一般錯誤訊息
	 */
	REQUEST_PARAM_ERROR(10000, "請求參數錯誤"), SYSTEM_ERROR(10001, "系統錯誤"),

	/**
	 * 員工資料錯誤訊息
	 */
	INSERT_EMPLOYEE_FAILURE(2001, "新增員工失敗"),

	;
	// 錯誤代碼
	private final Integer code;
	// 錯誤敘述
	private final String desc;

	private ResponseErrorCodeEntity(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ResponseErrorCodeEntity of(Integer code) {
		ResponseErrorCodeEntity status[] = ResponseErrorCodeEntity.values();
		for (ResponseErrorCodeEntity statu : status) {
			if (statu.code.equals(code)) {
				return statu;
			}
		}
		return null;
	}

	public static ResponseErrorCodeEntity of(String name) {
		ResponseErrorCodeEntity status[] = ResponseErrorCodeEntity.values();
		for (ResponseErrorCodeEntity statu : status) {
			if (statu.name().equals(name)) {
				return statu;
			}
		}
		return null;
	}
}
