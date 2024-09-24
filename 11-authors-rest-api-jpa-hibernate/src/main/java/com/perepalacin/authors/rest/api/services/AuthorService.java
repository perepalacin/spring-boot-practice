package com.perepalacin.authors.rest.api.services;

import com.perepalacin.authors.rest.api.domains.AuthorDao;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorDao createAuthor(AuthorDao authorDao);

    List<AuthorDao> findAll();

    Optional<AuthorDao> findById(Long id);
}
