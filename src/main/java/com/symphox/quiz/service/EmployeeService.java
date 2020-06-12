package com.symphox.quiz.service;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Employee;

/**
 * 員工資料服務介面層
 * 
 * @author Lucifers
 *
 */
public interface EmployeeService {
	/**
	 * 寫入或更新一筆員工資料
	 * 
	 * @param employee
	 * @return
	 */
	ResponseCodeEntity save(Employee employee);

	/**
	 * 刪除一筆員工資料
	 * 
	 * @param employee
	 * @return
	 */
	ResponseCodeEntity delete(Employee employee);

	/**
	 * 動態查詢員工資料
	 * 
	 * @param page
	 * @param id
	 * @param name
	 * @param age
	 * @param departmentName
	 * @return
	 */
	ResponseCodeEntity findBySpec(Integer page, Integer id, String name, Integer age, String departmentName);
}
