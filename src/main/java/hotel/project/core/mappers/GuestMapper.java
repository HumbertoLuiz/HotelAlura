package hotel.project.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hotel.project.core.models.Guest;
import hotel.project.web.dtos.GuestDto;

@Mapper(componentModel = "spring")
public interface GuestMapper {

	GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);

    Guest toModel(GuestDto form);

    GuestDto toForm(Guest model);
}
