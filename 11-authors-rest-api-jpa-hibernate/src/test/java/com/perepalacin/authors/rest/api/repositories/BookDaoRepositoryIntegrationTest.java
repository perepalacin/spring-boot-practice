package com.perepalacin.authors.rest.api.repositories;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.BookDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoRepositoryIntegrationTest {

    private BookRepository underTest;
    @Autowired
    public BookDaoRepositoryIntegrationTest(BookRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        BookDao bookDao = TestDataUtils.createTestBook(authorDao);
        //Spring JPA ManyToOne annotation takes care of saving the author
        underTest.save(bookDao);
        Optional<BookDao> result = underTest.findById(bookDao.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookDao);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        BookDao bookDao = TestDataUtils.createTestBook(authorDao);
        underTest.save(bookDao);
        bookDao.setTitle("LALALA!");
        underTest.save(bookDao);

        Optional<BookDao> result = underTest.findById(bookDao.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookDao);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        BookDao bookDaoA = TestDataUtils.createTestBookA(authorDao);
        underTest.save(bookDaoA);
        BookDao bookDaoB = TestDataUtils.createTestBookB(authorDao);
        underTest.save(bookDaoB);
        BookDao bookDaoC = TestDataUtils.createTestBookC(authorDao);
        underTest.save(bookDaoC);

        Iterable<BookDao> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactlyInAnyOrder(bookDaoA, bookDaoB, bookDaoC);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        BookDao bookDao = TestDataUtils.createTestBook(authorDao);
        underTest.save(bookDao);
        underTest.deleteById(bookDao.getIsbn());
        Optional<BookDao> result = underTest.findById(bookDao.getIsbn());
        assertThat(result).isEmpty();
    }

}
