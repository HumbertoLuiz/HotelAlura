package hotel.project.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hotel.project.core.models.Role;
import hotel.project.web.dtos.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	Role toModel(RoleDto form);

    RoleDto toForm(Role model);
        
}