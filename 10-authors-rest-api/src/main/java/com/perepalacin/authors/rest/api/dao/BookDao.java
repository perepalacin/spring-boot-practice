package com.perepalacin.authors.rest.api.dao;

import com.perepalacin.authors.rest.api.domains.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    void update(String isbn, Book book);

    void delete(String isbn);
}
