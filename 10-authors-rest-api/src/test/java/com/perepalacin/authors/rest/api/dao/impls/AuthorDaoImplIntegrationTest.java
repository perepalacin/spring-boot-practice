package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
}
