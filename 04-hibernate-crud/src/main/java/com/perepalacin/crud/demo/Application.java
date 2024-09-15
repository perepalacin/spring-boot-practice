package com.perepalacin.crud.demo;

import com.perepalacin.crud.demo.dao.StudentDAO;
import com.perepalacin.crud.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
//			System.out.println("Finding student with id 1: " + readStudent(studentDAO));
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
			deleteStudent(studentDAO);
//			deleteAll(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		Student tempStudent = new Student("Lucas", "Doe", "lucas@luv2code.com");

		studentDAO.save(tempStudent);

		System.out.println("Save student with generated id: " + tempStudent.getId());
	}

	private Student readStudent (StudentDAO studentDAO) {
		return studentDAO.findById(1);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Doe");
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student theStudent = studentDAO.findById(1);
		theStudent.setFirstName("Pepito");
		studentDAO.update(theStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		studentDAO.delete(studentId);
	}

	private void deleteAll(StudentDAO studentDAO) {
		studentDAO.deleteAll();
	}
}
