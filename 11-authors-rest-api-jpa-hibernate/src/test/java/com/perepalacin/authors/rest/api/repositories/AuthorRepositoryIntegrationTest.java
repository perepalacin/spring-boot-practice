package com.perepalacin.authors.rest.api.repositories;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.AuthorDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//TO RUN ALL THE TESTS: ./mvnw clean verify

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //Restarts the db and all the memory of the app based on the parameter you pass it. Play around with the arguments!
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    //WE import the implementation and not the interface because we are testing the impl!!
    //This is why we are adding the autowired, to make sure spring injects the proper dependency for the IT
    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        underTest.save(authorDao);
        Optional<AuthorDao> result = underTest.findById(authorDao.getId());
        assertThat(result).isPresent(); //Checks that it is not optinal therefore that the created author exists on the db
        assertThat(result.get()).isEqualTo(authorDao); //check that the author created is the one that we have created there.
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreateAndRecalled() {
        AuthorDao authorDaoA = TestDataUtils.createTestAuthorA();
        underTest.save(authorDaoA);
        AuthorDao authorDaoB = TestDataUtils.createTestAuthorB();
        underTest.save(authorDaoB);
        AuthorDao authorDaoC = TestDataUtils.createTestAuthorC();
        underTest.save(authorDaoC);

        Iterable<AuthorDao> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(authorDaoA, authorDaoB, authorDaoC);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        underTest.save(authorDao);
        authorDao.setAge(14);
        authorDao.setName("Luis Piquillo");
        underTest.save(authorDao);
        Optional<AuthorDao> result = underTest.findById(authorDao.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorDao);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorDao authorDao = TestDataUtils.createTestAuthor();
        underTest.save(authorDao);
        underTest.deleteById(authorDao.getId());
        Optional<AuthorDao> result = underTest.findById(authorDao.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan() {
        AuthorDao authorDaoA = TestDataUtils.createTestAuthorA();
        underTest.save(authorDaoA);
        AuthorDao authorDaoB = TestDataUtils.createTestAuthorB();
        underTest.save(authorDaoB);
        AuthorDao authorDaoC = TestDataUtils.createTestAuthorC();
        underTest.save(authorDaoC);

        Iterable<AuthorDao> result = underTest.ageLessThan(50);
        assertThat(result).containsExactlyInAnyOrder(authorDaoB, authorDaoC);
    }

    @Test
    public void testThatGetAuthorWithAgeGreaterThan() {
        AuthorDao authorDaoA = TestDataUtils.createTestAuthorA();
        underTest.save(authorDaoA);
        AuthorDao authorDaoB = TestDataUtils.createTestAuthorB();
        underTest.save(authorDaoB);
        AuthorDao authorDaoC = TestDataUtils.createTestAuthorC();
        underTest.save(authorDaoC);

        Iterable<AuthorDao> result = underTest.ageGreaterThan(50);
        assertThat(result).containsExactlyInAnyOrder(authorDaoA);
    }

}
