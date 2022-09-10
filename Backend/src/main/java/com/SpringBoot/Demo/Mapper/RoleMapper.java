package com.SpringBoot.Demo.Mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.SpringBoot.Demo.Dto.RoleDTO;
import com.SpringBoot.Demo.Entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role INSTANCE = Mappers.getMapper(Role.class);
    
    RoleDTO modelToDto(Role Role);

    List<RoleDTO> modelsToDtos(List<Role> Role);
 
    @InheritInverseConfiguration
    Role dtoToModel(RoleDTO RoleDTO);       

}
