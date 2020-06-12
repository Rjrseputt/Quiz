package com.symphox.quiz.exception;

/**
 * 資料庫相關的錯誤
 * 
 * @author Lucifers
 *
 */
public class DatabaseErrorsExceptions extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseErrorsExceptions(String message) {
		super(message);
	}
}
