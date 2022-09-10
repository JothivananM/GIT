package com.SpringBoot.Demo.Mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.SpringBoot.Demo.Dto.DesignationDTO;
import com.SpringBoot.Demo.Entity.Designation;

@Mapper(componentModel = "spring")
public interface DesignationMapper {

    Designation INSTANCE = Mappers.getMapper(Designation.class);
    
    DesignationDTO modelToDto(Designation Designation);

    List<DesignationDTO> modelsToDtos(List<Designation> Designation); 

    @InheritInverseConfiguration
    Designation dtoToModel(DesignationDTO DesignationDTO);    
    
}
