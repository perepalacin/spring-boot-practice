package com.perepalacin.authors.rest.api.services.impl;

import com.perepalacin.authors.rest.api.domains.BookDao;
import com.perepalacin.authors.rest.api.repositories.BookRepository;
import com.perepalacin.authors.rest.api.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDao createBook(String isbn, BookDao book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookDao> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookDao> findById(String isbn) {
        return bookRepository.findById(isbn);
    }
}
