package com.perepalacin.authors.rest.api.controllers;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.dto.AuthorDto;
import com.perepalacin.authors.rest.api.mappers.impls.AuthorMapper;
import com.perepalacin.authors.rest.api.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDao authorDao = authorMapper.mapFrom(authorDto);
        AuthorDao authorCreated = authorService.createAuthor(authorDao);
//        return authorMapper.mapTo(authorDao);
        return new ResponseEntity<AuthorDto>(authorMapper.mapTo(authorCreated), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorDao> authors = authorService.findAll();
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList()); //Convert it from iterable to list
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor (@PathVariable("id") Long id) {
        Optional<AuthorDao> foundAuthor = authorService.findById(id);
        return foundAuthor.map(authorDao -> {
            AuthorDto authorDto = authorMapper.mapTo(authorDao);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
