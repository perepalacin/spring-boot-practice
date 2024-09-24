package com.perepalacin.authors.rest.api.mappers.impls;

import com.perepalacin.authors.rest.api.domains.BookDao;
import com.perepalacin.authors.rest.api.domains.dto.BookDto;
import com.perepalacin.authors.rest.api.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookDao, BookDto> {

    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookDao bookDao) {
        return modelMapper.map(bookDao, BookDto.class);
    }

    @Override
    public BookDao mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookDao.class);
    }
}
