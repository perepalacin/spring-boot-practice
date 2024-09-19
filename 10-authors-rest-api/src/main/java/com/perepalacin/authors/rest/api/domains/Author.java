package com.perepalacin.authors.rest.api.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//These annotations spare us from having to do all this
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //Adds logging functionality
public class Author {
    //All fields are defined as wrapper classes because they may be nul!
    private Long id;
    private String name;
    private Integer age;
}
