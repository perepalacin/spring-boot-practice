package com.pere_palacin.OAuth.repositories;

import com.pere_palacin.OAuth.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {
    RegisteredUser findByUsername(String username);
}
