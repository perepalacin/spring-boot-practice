package com.perepalacin.authors.rest.api.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//These annotations spare us from having to do all this
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //Adds logging functionality
@Entity
@Table(name = "authors")
public class AuthorDao {
    //All fields are defined as wrapper classes because they may be nul!
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;
    private String name;
    private Integer age;
}
