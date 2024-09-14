package com.perepalacin.crud.demo;

import com.perepalacin.crud.demo.dao.StudentDAO;
import com.perepalacin.crud.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			System.out.println("Finding student with id 1: " + readStudent(studentDAO));
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
}
