package com.perepalacin.employeescrud.services;

import com.perepalacin.employeescrud.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
