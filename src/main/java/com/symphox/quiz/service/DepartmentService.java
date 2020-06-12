package com.symphox.quiz.service;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;

/**
 * 部門服務介面層
 * 
 * @author Lucifers
 *
 */
public interface DepartmentService {
	/**
	 * 寫入或更新一筆部門資料
	 * 
	 * @param department
	 * @return
	 */
	ResponseCodeEntity save(Department department);

	/**
	 * 刪除一筆部門資料
	 * 
	 * @param department
	 * @return
	 */
	ResponseCodeEntity delete(Department department);
}
