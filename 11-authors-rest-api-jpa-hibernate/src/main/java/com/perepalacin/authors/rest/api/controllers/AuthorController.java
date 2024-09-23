package com.perepalacin.authors.rest.api.controllers;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.dto.AuthorDto;
import com.perepalacin.authors.rest.api.mappers.impls.AuthorMapper;
import com.perepalacin.authors.rest.api.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDao authorDao = authorMapper.mapFrom(authorDto);
    }
}
