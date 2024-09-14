package com.perepalacin.crud.demo.dao;

import com.perepalacin.crud.demo.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);
}
