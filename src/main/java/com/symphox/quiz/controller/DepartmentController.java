package com.symphox.quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.symphox.quiz.entitys.ResponseCodeEntity;
import com.symphox.quiz.entitys.ResponseErrorCodeEntity;
import com.symphox.quiz.model.Department;
import com.symphox.quiz.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(path = "/department")
@Api(tags = "Department", description = "部門相關 API")
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/insert")
	@ApiOperation(value = "新增一筆部門資料")
	@ResponseBody
	public ResponseEntity<?> insert(
			@ApiParam(required = true, value = "部門名稱", example = "IT") @RequestParam(required = true) String name,
			@ApiParam(required = false, value = "部門描述", example = "Information Technology") @RequestParam(required = false) String description) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Department department = new Department();
			department.setName(name);
			department.setDescription(description);
			responseCodeEntity = departmentService.save(department);
		} catch (Exception e) {
			logger.error("新增部門資料失敗" + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("新增部門資料失敗") + e.getMessage());
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}

	@PostMapping("/update")
	@ApiOperation(value = "更新一筆部門資料")
	@ResponseBody
	public ResponseEntity<?> update(
			@ApiParam(required = true, value = "部門編號", example = "1") @RequestParam(required = true) Integer id,
			@ApiParam(required = false, value = "部門名稱", example = "IT") @RequestParam(required = false) String name,
			@ApiParam(required = false, value = "部門描述", example = "Information Technology") @RequestParam(required = false) String description) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Department department = new Department();
			department.setId(id);
			department.setName(name);
			department.setDescription(description);
			responseCodeEntity = departmentService.save(department);
		} catch (Exception e) {
			logger.error("更新部門資料失敗" + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("更新部門資料失敗") + e.getMessage());
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}	

	@PostMapping("/delete")
	@ApiOperation(value = "刪除一筆部門資料")
	@ResponseBody
	public ResponseEntity<?> delete(
			@ApiParam(required = true, value = "部門編號", example = "1") @RequestParam(required = true) Integer id) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Department department = new Department();
			department.setId(id);
			responseCodeEntity = departmentService.delete(department);
		} catch (Exception e) {
			logger.error("刪除部門資料失敗" + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("刪除部門資料失敗") + e.getMessage());
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}
}