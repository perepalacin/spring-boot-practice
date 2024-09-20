package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.dao.AuthorDao;
import com.perepalacin.authors.rest.api.domains.Author;
import com.perepalacin.authors.rest.api.domains.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthor();
        authorDao.create(author);
        Book bookA = TestDataUtils.createTestBookA();
        underTest.create(bookA);
        Book bookB = TestDataUtils.createTestBookB();
        underTest.create(bookB);
        Book bookC = TestDataUtils.createTestBookC();
        underTest.create(bookC);

        List<Book> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactlyInAnyOrder(bookA, bookB, bookC);
    }

}
