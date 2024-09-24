package com.perepalacin.authors.rest.api.controllers;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.BookDao;
import com.perepalacin.authors.rest.api.domains.dto.AuthorDto;
import com.perepalacin.authors.rest.api.domains.dto.BookDto;
import com.perepalacin.authors.rest.api.mappers.Mapper;
import com.perepalacin.authors.rest.api.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private Mapper<BookDao, BookDto> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookDao, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
            @PathVariable String isbn,
            @RequestBody BookDto bookDto
    ) {
        BookDao bookDao = bookMapper.mapFrom(bookDto);
        BookDao savedBookDao = bookService.createBook(isbn, bookDao);
        BookDto savedBookDto = bookMapper.mapTo(savedBookDao);
        return new ResponseEntity<BookDto>(savedBookDto,HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> listBooks() {
        List<BookDao> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList()); //Convert it from iterable to list
    }


    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getAuthor (@PathVariable("isbn") String isbn) {
        Optional<BookDao> foundBook = bookService.findById(isbn);
        return foundBook.map(bookDao -> {
            BookDto bookDto = bookMapper.mapTo(bookDao);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
