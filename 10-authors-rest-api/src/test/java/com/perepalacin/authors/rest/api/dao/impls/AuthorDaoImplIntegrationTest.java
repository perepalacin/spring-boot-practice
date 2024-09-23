package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//TO RUN ALL THE TESTS: ./mvnw clean verify

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //Restarts the db and all the memory of the app based on the parameter you pass it. Play around with the arguments!
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    //WE import the implementation and not the interface because we are testing the impl!!
    //This is why we are adding the autowired, to make sure spring injects the proper dependency for the IT
    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtils.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent(); //Checks that it is not optinal therefore that the created author exists on the db
        assertThat(result.get()).isEqualTo(author); //check that the author created is the one that we have created there.
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreateAndRecalled() {
        Author authorA = TestDataUtils.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtils.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtils.createTestAuthorC();
        underTest.create(authorC);

        List<Author> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtils.createTestAuthor();
        underTest.create(author);
        author.setAge(14);
        author.setName("Luis Piquillo");
        underTest.update(1L, author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtils.createTestAuthor();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isEmpty();

    }
}
