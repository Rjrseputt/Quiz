package com.symphox.quiz.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.symphox.quiz.model.Department;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void testRepository() {
		// given
		Department department = new Department();
		department.setName("IS");

		// test save
		Department resultd = departmentRepository.save(department);
		Assert.assertNotNull(department.getId());
		// test delete
		departmentRepository.delete(resultd);
	}
}
