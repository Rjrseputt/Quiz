package com.symphox.quiz.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.model.Employee;
import com.symphox.quiz.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EmployeeControllerTest {
	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	Employee employee = new Employee();
	Mockito mockito;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		// Initial employee
		employee.setId(1);
		employee.setName("Lucifers");
		employee.setDptId(1);
		employee.setGender("M");
		employee.setAddress("Taiwan");
		employee.setAge(18);
		employee.setPhone("0900123456");
	}

	@Test
	public void testInsert() {
		// when
		lenient().when(employeeService.save(employee)).thenReturn(new ResponseCodeEntity(employee));
		// test
		ResponseEntity<?> responseEntity = employeeController.insert("Lucifers", 1, "M", "0900123456", "Taiwan", 18);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testUpdate() {
		// when
		lenient().when(employeeService.save(employee)).thenReturn(new ResponseCodeEntity(employee));
		// test
		ResponseEntity<?> responseEntity = employeeController.update(1, "Lucifers", 10001, "M", "0900123456", "Taiwan",
				18);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testDelete() {
		// when
		lenient().when(employeeService.delete(employee)).thenReturn(new ResponseCodeEntity());
		// test
		ResponseEntity<?> responseEntity = employeeController.delete(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testdynamicQuery() {
		// given
		Department department = new Department();
		department.setName("IT");

		employee.setDepartment(department);
		// when
		lenient().when(employeeService.findBySpec(0, 1, "Lucifers", 18, "IT"))
				.thenReturn(new ResponseCodeEntity(employee));
		// test
		ResponseEntity<?> responseEntity = employeeController.dynamicQuery(0, 1, "Lucifers", 18, "IT");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
}
