package hotel.project.web.services;

import java.util.List;

import hotel.project.core.models.Guest;
import hotel.project.web.dtos.GuestDto;

public interface GuestService {

	List<Guest> findAll();

	Guest save(GuestDto form);

	Guest findById(Long id);

	Guest update(GuestDto form, Long id);

	void deleteById(Long id);
}
