package com.svv.customExceptionHandling.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.svv.customExceptionHandling.Entity.Employee;
import com.svv.customExceptionHandling.Service.EmployeeServiceInterface;
import com.svv.customExceptionHandling.custom.exception.BusinessException;
import com.svv.customExceptionHandling.custom.exception.ControllerException;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeServiceInterface EmpService ;
	
	@PostMapping("/save")
	ResponseEntity<?> saveUser(@RequestBody Employee empl)
	{
		try {
			Employee employeeSaved =  EmpService.saveEmploye(empl);
			return new ResponseEntity<>(employeeSaved, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/all")
	ResponseEntity<?> getAllEmployee()
	{
		List<Employee> empList = EmpService.getAllEmployee();
		return new ResponseEntity<>(empList,HttpStatus.OK);
	}
	@GetMapping("/all/{id}")
	ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id)
	{
		Employee emp = EmpService.getEmployeById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long id)
	{
		EmpService.deleteEmployeById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
