package com.perepalacin.crud.demo.dao;

import com.perepalacin.crud.demo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired //this annotation is optional, since we only have one constructor
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager =entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        //This will save the user directly to the db.
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

}
