package com.perepalacin.spring_security_intro.repositories;

import com.perepalacin.spring_security_intro.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
