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


    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("Jonny and the Stone")
                .authorId(1L)
                .build();
    }
}
