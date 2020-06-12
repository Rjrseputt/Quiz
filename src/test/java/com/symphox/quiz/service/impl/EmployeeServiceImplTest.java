package com.symphox.quiz.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.model.Employee;
import com.symphox.quiz.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave_basic() {
		// given
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Lucifers");
		employee.setDptId(1);
		employee.setGender("M");
		employee.setAddress("Taiwan");
		employee.setAge(18);
		employee.setPhone("0900123456");

		// when
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		// test
		ResponseCodeEntity response = employeeServiceImpl.save(employee);

		Employee result = (Employee) response.getDatas();
		assertEquals(result.getId(), 1);
	}

	@Test
	public void testDelete_basic() {
		// given
		Employee employee = new Employee();
		employee.setId(1);
		// test
		ResponseCodeEntity response = employeeServiceImpl.delete(employee);
		assertNotNull(response.getDatas());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testQuery_basic() {
		// given
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Lucifers");
		employee.setDptId(1);
		employee.setGender("M");
		employee.setAddress("Taiwan");
		employee.setAge(18);
		employee.setPhone("0900123456");

		Department department = new Department();
		department.setName("IT");

		employee.setDepartment(department);

		List<Employee> lists = new ArrayList<Employee>();
		lists.add(employee);

		Page<Employee> pages = new PageImpl<>(lists, PageRequest.of(0, 10), lists.size());

		// when
		when(employeeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pages);

		// test
		ResponseCodeEntity response = employeeServiceImpl.findBySpec(0, 1, "Lucifers", 18, "IT");

		List<Employee> result = (List<Employee>) response.getDatas();
		assertEquals(result.get(0).getId(), 1);
		assertEquals(result.get(0).getName(), "Lucifers");
		assertEquals(result.get(0).getAge(), 18);
		assertEquals(result.get(0).getDepartment().getName(), "IT");
	}
}
