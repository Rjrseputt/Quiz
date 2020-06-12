package com.symphox.quiz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.repository.DepartmentRepository;
import com.symphox.quiz.service.DepartmentService;

/**
 * 部門服務實做層
 * 
 * @author Lucifers
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public ResponseCodeEntity save(Department department) {
		// 因 CrudRepository 的 Save 為 insert or update 所以用同一個
		return new ResponseCodeEntity(departmentRepository.save(department));
	}

	@Override
	public ResponseCodeEntity delete(Department department) {
		// 進行刪除
		departmentRepository.delete(department);
		return new ResponseCodeEntity();
	}
}
