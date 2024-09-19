package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.dao.AuthorDao;
import com.perepalacin.authors.rest.api.domains.Author;
import com.perepalacin.authors.rest.api.domains.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {

    private BookDaoImpl underTest;
    private AuthorDao authorDao;
    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao){
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthor();
        authorDao.create(author);
        Book book = TestDataUtils.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.findByIsbn(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
