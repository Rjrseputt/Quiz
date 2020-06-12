package com.symphox.quiz.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.symphox.quiz.model.Department;
import com.symphox.quiz.model.Employee;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void testRepository() {
		Department department = new Department();
		department.setName("IS");
		Department resultd = departmentRepository.save(department);
		
		Employee employee = new Employee();
		employee.setName("Lucifers");
		employee.setDptId(resultd.getId());
		employee.setGender("M");
		employee.setAddress("Taiwan");
		employee.setAge(18);
		employee.setPhone("0900123456");

		Employee resulte = employeeRepository.save(employee);
		Assert.assertNotNull(resulte.getId());
		
		employeeRepository.delete(resulte);
		departmentRepository.delete(resultd);
	}

	@Test
	public void testRepositoryFindAll() {
		List<Employee> lists = (List<Employee>) employeeRepository.findAll();
		System.out.println(lists.size());
//		assertEquals(1, lists.size());
		Assert.assertNotNull(lists);
	}
}
