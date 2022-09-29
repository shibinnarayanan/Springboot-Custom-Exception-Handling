package com.svv.customExceptionHandling.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.svv.customExceptionHandling.Entity.Employee;



public interface EmployeeServiceInterface{
	
	List<Employee> getAllEmployee() ;
	
	Employee saveEmploye(Employee empl);
	
	Employee getEmployeById(Long Id);
	
	void deleteEmployeById(Long Id);
}
