package com.perepalacin.authors.rest.api.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookDao {

    @Id
    private String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL) //We swap the id for the object it relates to and we provide the type of relationship on top.
    @JoinColumn(name = "author_id")
    private AuthorDao authorDao;
}
