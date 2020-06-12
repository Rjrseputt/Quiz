package com.symphox.quiz.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DepartmentServiceImplTest {

	@Mock
	DepartmentRepository departmentRepository;

	@InjectMocks
	DepartmentServiceImpl departmentServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave_basic() {
		// given
		Department department = new Department();
		department.setId(1);
		department.setName("IA");
		department.setDescription("IA Department");

		// when
		when(departmentRepository.save(any(Department.class))).thenReturn(department);

		// test
		ResponseCodeEntity response = departmentServiceImpl.save(department);

		Department result = (Department) response.getDatas();
		assertEquals(result.getId(), 1);
		assertEquals(result.getName(), "IA");
		assertEquals(result.getDescription(), "IA Department");
	}

	@Test
	public void testDelete_basic() {
		// test
		ResponseCodeEntity response = departmentServiceImpl.delete(any(Department.class));
		assertNotNull(response.getDatas());
	}
}
