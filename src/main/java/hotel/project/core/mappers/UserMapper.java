package hotel.project.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import hotel.project.core.models.User;
import hotel.project.web.dtos.UserDto;
import hotel.project.web.dtos.UserUpdateDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "password", source = "password")
	User toModel(UserDto form);

	User toModel(UserUpdateDto form);

	UserUpdateDto toForm(User model);
}
