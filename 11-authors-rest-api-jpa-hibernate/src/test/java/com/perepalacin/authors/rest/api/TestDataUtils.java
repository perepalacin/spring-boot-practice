package com.perepalacin.authors.rest.api;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.BookDao;

//Class meant to store objects and data for the tests!
public final class TestDataUtils {
    private TestDataUtils() {

    }

    public static AuthorDao createTestAuthor() {
        return AuthorDao.builder()
                .id(1L)
                .name("Pere Palacín")
                .age(88)
                .build();
    }

    public static AuthorDao createTestAuthorB() {
        return AuthorDao.builder()
                .id(2L)
                .name("Lucas Molho")
                .age(36)
                .build();
    }

    public static AuthorDao createTestAuthorA() {
        return AuthorDao.builder()
                .id(1L)
                .name("Pere Palacín")
                .age(88)
                .build();
    }

    public static AuthorDao createTestAuthorC() {
        return AuthorDao.builder()
                .id(3L)
                .name("Laura Perejil")
                .age(41)
                .build();
    }


    public static BookDao createTestBook(final AuthorDao authorDao) {
        return BookDao.builder()
                .isbn("978-1-2345-6789-0")
                .title("Jonny and the Stone")
                .authorDao(authorDao)
                .build();
    }

    public static BookDao createTestBookA(final AuthorDao authorDao) {
        return BookDao.builder()
                .isbn("978-1-2345-6781-0")
                .title("Lily and the Stone")
                .authorDao(authorDao)
                .build();
    }

    public static BookDao createTestBookB(final AuthorDao authorDao) {
        return BookDao.builder()
                .isbn("978-1-3412-6789-0")
                .title("Samuel and the Stone")
                .authorDao(authorDao)
                .build();
    }

    public static BookDao createTestBookC(final AuthorDao authorDao) {
        return BookDao.builder()
                .isbn("148-1-2345-6789-0")
                .title("Jackass and the Stone")
                .authorDao(authorDao)
                .build();
    }
}
