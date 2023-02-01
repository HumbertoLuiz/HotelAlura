package hotel.project.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import hotel.project.core.models.Reserve;
import hotel.project.web.dtos.ReserveDto;

@Mapper(componentModel = "spring")
public interface ReserveMapper {

	ReserveMapper INSTANCE = Mappers.getMapper(ReserveMapper.class);

	Reserve toModel(ReserveDto form);

    ReserveDto toForm(Reserve model);
        
}
