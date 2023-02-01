package hotel.project.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.project.core.exceptions.ServiceNotFoundException;
import hotel.project.core.mappers.GuestMapper;
import hotel.project.core.models.Guest;
import hotel.project.core.repository.GuestRepository;
import hotel.project.web.dtos.GuestDto;
import hotel.project.web.services.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private GuestMapper guestMapper;

	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	public Guest save(GuestDto form) {
		var model = guestMapper.toModel(form);

		return guestRepository.save(model);
	}

	public Guest findById(Long id) {
		var guestFound = guestRepository.findById(id);

		if (guestFound.isPresent()) {
			return guestFound.get();
		}

		var message = String.format("Service with ID %d not found", id);
		throw new ServiceNotFoundException(message);
	}

	public Guest update(GuestDto form, Long id) {
		var guestFound = findById(id);

		var model = guestMapper.toModel(form);
		((Guest) model).setId(guestFound.getId());

		return guestRepository.save(model);
	}

	public void deleteById(Long id) {
		var guestFound = findById(id);

		guestRepository.delete(guestFound);
	}

}
