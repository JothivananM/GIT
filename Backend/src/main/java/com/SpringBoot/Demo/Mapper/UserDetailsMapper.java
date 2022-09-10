package com.SpringBoot.Demo.Mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.SpringBoot.Demo.Dto.UserDetailsDTO;
import com.SpringBoot.Demo.Entity.UserDetails;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetails INSTANCE = Mappers.getMapper(UserDetails.class);
    
    UserDetailsDTO modelToDto(UserDetails userDetails);

    List<UserDetailsDTO> modelsToDtos(List<UserDetails> userDetails);
 
    @InheritInverseConfiguration
    UserDetails dtoToModel(UserDetailsDTO userDetailsDTO);       

}
