package com.symphox.quiz.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.model.Employee;
import com.symphox.quiz.repository.EmployeeRepository;
import com.symphox.quiz.service.EmployeeService;

/**
 * 員工資料服務實做層
 * 
 * @author Lucifers
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ResponseCodeEntity save(Employee employee) {
		// 因 CrudRepository 的 Save 為 insert or update 所以用同一個
		return new ResponseCodeEntity(employeeRepository.save(employee));
	}

	@Override
	public ResponseCodeEntity delete(Employee employee) {
		// 進行刪除
		employeeRepository.delete(employee);
		return new ResponseCodeEntity();
	}

	@Override
	public ResponseCodeEntity findBySpec(Integer page, Integer id, String name, Integer age, String departmentName) {
		// 產生 Sort 條件，但注意 page - 1 不可小於 0，小於 0 ，直接為 0
		PageRequest pageReq = PageRequest.of((page - 1) < 0 ? 0 : (page - 1), 10, Sort.by(Sort.Direction.ASC, "id"));
		// 產生 SQL 查詢條件
		Specification<Employee> spec = getSpec(id, name, age, departmentName);
		// 進行查詢
		List<Employee> list = employeeRepository.findAll(spec, pageReq).toList();
		return new ResponseCodeEntity(list);
	}

	/**
	 * 依照所傳參數，組成 SQL 查詢條件
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param departmentName
	 * @return
	 */
	private Specification<Employee> getSpec(Integer id, String name, Integer age, String departmentName) {
		return new Specification<Employee>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate p1 = null;
				// 若有傳入 id 參數，組成以下條件 SQL: id = id
				if (id != null) {
					Predicate p2 = cb.equal(root.get("id"), id);
					p1 = p2;
				}
				// 若有傳入 name 參數，組成以下條件 SQL: name like '%name%'
				if (name != null && !name.isEmpty()) {
					Predicate p2 = cb.like(root.get("name"), "%" + name + "%");
					// 若已有條件，加上 and 條件
					if (p1 != null) {
						p1 = cb.and(p1, p2);
					} else {
						p1 = p2;
					}
				}
				// 若有傳入 age 參數，組成以下條件 SQL: age = age
				if (age != null) {
					Predicate p2 = cb.equal(root.get("age"), age);
					// 若已有條件，加上 and 條件
					if (p1 != null) {
						p1 = cb.and(p1, p2);
					} else {
						p1 = p2;
					}
				}
				// 若有傳入 departmentName 參數，組成以下條件 SQL: departmentName like '%departmentName%'
				if (departmentName != null && !departmentName.isEmpty()) {
					// 因部門名稱為 Department 資料表，所以需 JOIN 進來查詢
					Join<Employee, Department> join = root.join("department", JoinType.INNER);
					Predicate p2 = cb.like(join.get("name"), "%" + departmentName + "%");
					// 若已有條件，加上 and 條件
					if (p1 != null) {
						p1 = cb.and(p1, p2);
					} else {
						p1 = p2;
					}
				}
				return p1;
			}
		};
	}
}
