package com.symphox.quiz.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DepartmentControllerTest {

	@InjectMocks
	DepartmentController departmentController;

	@Mock
	DepartmentService departmentService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInsert() {
		// given
		Department department = new Department();
		department.setId(1);
		department.setName("IT");
		department.setDescription("IT Department");

		// when
		when(departmentService.save(any(Department.class))).thenReturn(new ResponseCodeEntity(department));

		// test
		ResponseEntity<?> responseEntity = departmentController.insert("IT", "IT Department");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testUpdate() {
		// given
		Department department = new Department();
		department.setId(1);
		department.setName("IT");
		department.setDescription("IT Department");

		// when
		when(departmentService.save(any(Department.class))).thenReturn(new ResponseCodeEntity(department));

		// test
		ResponseEntity<?> responseEntity = departmentController.update(1, "IT", "IT Department");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testDelete() {
		// given
		Department department = new Department();
		department.setId(1);
		// when
		lenient().when(departmentService.delete(department)).thenReturn(new ResponseCodeEntity());
		// test
		ResponseEntity<?> responseEntity = departmentController.delete(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
}
