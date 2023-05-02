package com.project.phoneshop.mapper;

import com.project.phoneshop.dto.BrandDTO;
import com.project.phoneshop.dto.ModelDTO;
import com.project.phoneshop.model.Brand;
import com.project.phoneshop.model.Model;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T17:05:55+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class ModelMapperImpl implements ModelMapper {

    @Override
    public Model toModel(ModelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Model model = new Model();

        model.setBrand( brandDTOToBrand( dto.getBrandDTO() ) );
        model.setId( dto.getId() );
        model.setName( dto.getName() );

        return model;
    }

    @Override
    public ModelDTO toDTO(Model entity) {
        if ( entity == null ) {
            return null;
        }

        ModelDTO modelDTO = new ModelDTO();

        modelDTO.setBrandDTO( brandToBrandDTO( entity.getBrand() ) );
        modelDTO.setId( entity.getId() );
        modelDTO.setName( entity.getName() );

        return modelDTO;
    }

    protected Brand brandDTOToBrand(BrandDTO brandDTO) {
        if ( brandDTO == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDTO.getId() );
        brand.setName( brandDTO.getName() );

        return brand;
    }

    protected BrandDTO brandToBrandDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setId( brand.getId() );
        brandDTO.setName( brand.getName() );

        return brandDTO;
    }
}
