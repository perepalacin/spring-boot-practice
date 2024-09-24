package com.perepalacin.authors.rest.api.mappers.impls;

import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.dto.AuthorDto;
import com.perepalacin.authors.rest.api.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//This is 100% optional, but it's just to keep good practices going on!
@Component
public class AuthorMapper implements Mapper<AuthorDao, AuthorDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorDao authorDao) {
        return modelMapper.map(authorDao, AuthorDto.class);
    }

    @Override
    public AuthorDao mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorDao.class);
    }
}
