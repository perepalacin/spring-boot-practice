package com.perepalacin.authors.rest.api.services.impl;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.repositories.AuthorRepository;
import com.perepalacin.authors.rest.api.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDao createAuthor(AuthorDao authorDao) {
        return authorRepository.save(authorDao);
    }

    @Override
    public List<AuthorDao> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDao> findById(Long id) {
        return authorRepository.findById(id);
    }
}
