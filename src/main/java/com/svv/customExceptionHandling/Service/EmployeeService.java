package com.svv.customExceptionHandling.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svv.customExceptionHandling.Entity.Employee;
import com.svv.customExceptionHandling.Repository.Repository;
import com.svv.customExceptionHandling.custom.exception.BusinessException;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	Repository userRepo;

	@Override
	public List<Employee> getAllEmployee() {
		return userRepo.findAll();
	}

	@Override
	public Employee saveEmploye(Employee empl) {

		//Note -- try block should not give for validation.If we pub try block for validation then it will 
		//throw exception and catch Exception instead of Business Exception
		 
		if (empl.getName().isEmpty() || empl.getName().length() == 0) {
			// 1001 -> is custome error code
			throw new BusinessException("1001", "Name is receive as empty or blank");
		}

		try {
			return userRepo.save(empl);

		} catch (IllegalArgumentException e) {
			throw new BusinessException("1002", "given employee is null " + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("1003", "something went wrong in service layer " + e.getMessage());
		}

	}

	@Override
	public Employee getEmployeById(Long Id) {
		return userRepo.findByid(Id);
	};

	@Override
	public void deleteEmployeById(Long Id) {
		userRepo.deleteById(Id);
	}

}
