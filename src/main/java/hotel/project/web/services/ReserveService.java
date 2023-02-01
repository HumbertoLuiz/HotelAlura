package hotel.project.web.services;

import java.util.List;

import hotel.project.core.models.Reserve;
import hotel.project.web.dtos.ReserveDto;

public interface ReserveService {
	 
	List<Reserve> findAll();

	Reserve save(ReserveDto reserveDto);

	Reserve findById(Long id);

	Reserve update(ReserveDto form, Long id);

	void deleteById(Long id);
}