package com.SpringBoot.Demo.Mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.SpringBoot.Demo.Dto.SidebarMenuDTO;
import com.SpringBoot.Demo.Entity.SidebarMenu;

@Mapper(componentModel = "spring")
public interface SidebarMenuMapper {

    SidebarMenu INSTANCE = Mappers.getMapper(SidebarMenu.class);
    
    SidebarMenuDTO modelToDto(SidebarMenu SidebarMenu);

    List<SidebarMenuDTO> modelsToDtos(List<SidebarMenu> SidebarMenu); 

    @InheritInverseConfiguration
    SidebarMenu dtoToModel(SidebarMenuDTO SidebarMenuDTO);       

}
