package com.perepalacin.authors.rest.api.services;

import com.perepalacin.authors.rest.api.domains.AuthorDao;

public interface AuthorService {
    AuthorDao createAuthor(AuthorDao authorDao);
}
