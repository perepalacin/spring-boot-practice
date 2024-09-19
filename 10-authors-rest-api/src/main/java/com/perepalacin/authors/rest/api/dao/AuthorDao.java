package com.perepalacin.authors.rest.api.dao;

import com.perepalacin.authors.rest.api.domains.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findById(long authorId);
}
