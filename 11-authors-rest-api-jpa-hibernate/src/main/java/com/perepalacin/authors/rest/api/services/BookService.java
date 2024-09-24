package com.perepalacin.authors.rest.api.services;

import com.perepalacin.authors.rest.api.domains.BookDao;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDao createBook(String isbn, BookDao book);

    List<BookDao> findAll();

    Optional<BookDao> findById(String isbn);
}
