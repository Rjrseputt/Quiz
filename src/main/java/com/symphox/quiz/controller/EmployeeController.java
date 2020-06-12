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
import com.symphox.quiz.model.Employee;
import com.symphox.quiz.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(path = "/employee")
@Api(tags = "EMPLOYEE", description = "員工相關 API")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/insert")
	@ApiOperation(value = "新增一筆員工資料")
	@ResponseBody
	public ResponseEntity<?> insert(
			@ApiParam(required = true, value = "員工名稱", example = "Lucifers") @RequestParam(required = true) String name,
			@ApiParam(required = true, value = "部門編號", example = "1") @RequestParam(required = true) Integer departmentId,
			@ApiParam(required = true, value = "員工性別", example = "M") @RequestParam(required = true) String gender,
			@ApiParam(required = true, value = "員工電話", example = "0900123456") @RequestParam(required = true) String phone,
			@ApiParam(required = true, value = "員工地址", example = "Taiwan") @RequestParam(required = true) String address,
			@ApiParam(required = true, value = "員工年齡", example = "25") @RequestParam(required = true) Integer age) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Employee employee = new Employee();
			employee.setName(name);
			employee.setDptId(departmentId);
			employee.setGender(gender);
			employee.setPhone(phone);
			employee.setAddress(address);
			employee.setAge(age);
			responseCodeEntity = employeeService.save(employee);
		} catch (Exception e) {
			logger.error("新增員工資料失敗: " + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("新增員工資料失敗" + e.getMessage()));
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}

	@PostMapping("/update")
	@ApiOperation(value = "修改一筆員工資料")
	@ResponseBody
	public ResponseEntity<?> update(
			@ApiParam(required = true, value = "員工編號", example = "1") @RequestParam(required = true) Integer id,
			@ApiParam(required = false, value = "員工名稱", example = "Lucifers") @RequestParam(required = false) String name,
			@ApiParam(required = false, value = "部門編號", example = "1") @RequestParam(required = false) Integer departmentId,
			@ApiParam(required = false, value = "員工性別", example = "M") @RequestParam(required = false) String gender,
			@ApiParam(required = false, value = "員工電話", example = "0900123456") @RequestParam(required = false) String phone,
			@ApiParam(required = false, value = "員工地址", example = "Taiwan") @RequestParam(required = false) String address,
			@ApiParam(required = false, value = "員工年齡", example = "25") @RequestParam(required = false) Integer age) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Employee employee = new Employee();
			employee.setId(id);
			employee.setName(name);
			employee.setDptId(departmentId);
			employee.setGender(gender);
			employee.setPhone(phone);
			employee.setAddress(address);
			employee.setAge(age);
			responseCodeEntity = employeeService.save(employee);
		} catch (Exception e) {
			logger.error("更改員工資料失敗" + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("更改員工資料失敗" + e.getMessage()));
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}

	@PostMapping("/delete")
	@ApiOperation(value = "刪除一筆員工資料")
	@ResponseBody
	public ResponseEntity<?> delete(
			@ApiParam(required = true, value = "員工編號", example = "1") @RequestParam(required = true) Integer id) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			Employee employee = new Employee();
			employee.setId(id);
			responseCodeEntity = employeeService.delete(employee);
		} catch (Exception e) {
			logger.error("刪除員工資料失敗" + e.getMessage());
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("刪除員工資料失敗" + e.getMessage()));
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}

	@PostMapping("/dynamicQuery")
	@ApiOperation(value = "動態條件查詢員工資料")
	@ResponseBody
	public ResponseEntity<?> dynamicQuery(
			@ApiParam(required = true, value = "分頁編號", example = "1") @RequestParam(required = true) Integer page,
			@ApiParam(required = false, value = "員工編號", example = "1") @RequestParam(required = false) Integer id,
			@ApiParam(required = false, value = "員工名稱", example = "Lucifers") @RequestParam(required = false) String name,
			@ApiParam(required = false, value = "員工年齡", example = "25") @RequestParam(required = false) Integer age,
			@ApiParam(required = false, value = "部門名稱", example = "IT") @RequestParam(required = false) String departmentName) {
		ResponseCodeEntity responseCodeEntity = null;
		try {
			responseCodeEntity = employeeService.findBySpec(page, id, name, age, departmentName);
		} catch (Exception e) {
			logger.error("動態條件查詢員工資料失敗" + e.getMessage());
			e.printStackTrace();
			responseCodeEntity = new ResponseCodeEntity(ResponseErrorCodeEntity.of("動態條件查詢員工資料失敗") + e.getMessage());
		}
		return ResponseEntity.ok().body(responseCodeEntity);
	}
}