package com.perepalacin.authors.rest.api.dao;

import com.perepalacin.authors.rest.api.domains.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findByIsbn(String isbn);
}
