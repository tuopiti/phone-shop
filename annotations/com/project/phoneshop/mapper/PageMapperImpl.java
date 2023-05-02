package com.project.phoneshop.mapper;

import com.project.phoneshop.dto.PageDTO;
import com.project.phoneshop.dto.PaginationDTO;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T17:05:56+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class PageMapperImpl implements PageMapper {

    @Override
    public PageDTO toDTO(Page<?> page) {
        if ( page == null ) {
            return null;
        }

        PageDTO pageDTO = new PageDTO();

        pageDTO.setPagination( toPaginationDTO(page) );
        pageDTO.setList( page.getContent() );

        return pageDTO;
    }

    @Override
    public PaginationDTO toPaginationDTO(Page<?> page) {
        if ( page == null ) {
            return null;
        }

        PaginationDTO.PaginationDTOBuilder paginationDTO = PaginationDTO.builder();

        paginationDTO.numberOfElements( page.getNumberOfElements() );
        paginationDTO.number( page.getNumber() );
        paginationDTO.size( page.getSize() );
        paginationDTO.totalElements( page.getTotalElements() );
        paginationDTO.totalPages( page.getTotalPages() );
        paginationDTO.empty( page.isEmpty() );
        paginationDTO.first( page.isFirst() );
        paginationDTO.last( page.isLast() );

        return paginationDTO.build();
    }
}
