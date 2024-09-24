package com.perepalacin.authors.rest.api.domains.dto;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String isbn;
    private String title;
    private AuthorDto authorDao;
}
