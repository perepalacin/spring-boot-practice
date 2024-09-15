package com.perepalacin.employeescrud.DAO;

import com.perepalacin.employeescrud.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
