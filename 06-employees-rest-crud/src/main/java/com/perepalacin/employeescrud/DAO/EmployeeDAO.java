package com.perepalacin.employeescrud.DAO;


import com.perepalacin.employeescrud.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
