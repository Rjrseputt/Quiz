package com.symphox.quiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.symphox.quiz.model.Department;

/**
 * 
 * @author Lucifers
 *
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
