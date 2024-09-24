package com.perepalacin.authors.rest.api.repositories;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorDao, Long> {

    Iterable<AuthorDao> ageLessThan(int age);

//    @Query("SELECT a FROM Author a WHERE a.age > ?1") //?1 refers to 1st param of the function
//    Iterable<AuthorDao> ageGreaterThan(int age);

}
