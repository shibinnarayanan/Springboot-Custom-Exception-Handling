package com.svv.customExceptionHandling.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.svv.customExceptionHandling.Entity.Employee;


public interface Repository extends JpaRepository<Employee,Long>{
  
	
	Employee findByid(Long Id);

}
