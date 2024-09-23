package com.perepalacin.authors.rest.api.repositories;

import com.perepalacin.authors.rest.api.domains.BookDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookDao, String> {
}
