package com.perepalacin.authors.rest.api;

import com.perepalacin.authors.rest.api.domains.Author;
import com.perepalacin.authors.rest.api.domains.Book;

//Class meant to store objects and data for the tests!
public final class TestDataUtils {
    private TestDataUtils() {

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Pere Palacín")
                .age(88)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Pere Palacín")
                .age(88)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Lucas Molho")
                .age(36)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Laura Perejil")
                .age(61)
                .build();
    }


    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("Jonny and the Stone")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-1-2345-6781-0")
                .title("Lily and the Stone")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-1-3412-6789-0")
                .title("Samuel and the Stone")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("148-1-2345-6789-0")
                .title("Jackass and the Stone")
                .authorId(1L)
                .build();
    }
}
