package com.perepalacin.authors.rest.api.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //Generates getters and setters
//This is just a POJO to transfer data from persistance layer to presentation layer
public class AuthorDto {
    private Long id;
    private String name;
    private Integer age;
}
