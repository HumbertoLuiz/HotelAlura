package hotel.project.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.project.core.exceptions.ServiceNotFoundException;
import hotel.project.core.mappers.ReserveMapper;
import hotel.project.core.models.Reserve;
import hotel.project.core.repository.ReserveRepository;
import hotel.project.web.dtos.ReserveDto;
import hotel.project.web.services.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	private ReserveRepository reserveRepository;

	@Autowired
	private ReserveMapper reserveMapper;

	public List<Reserve> findAll() {
		return reserveRepository.findAll();
	}

	public Reserve save(ReserveDto reserveDto) {
		
		var model = reserveMapper.toModel(reserveDto);
		
		return reserveRepository.save(model);
	}
		
	public Reserve findById(Long id) {
		var reserveFound = reserveRepository.findById(id);

		if (reserveFound.isPresent()) {
			return reserveFound.get();
		}

		var message = String.format("Service with ID %d not found", id);
		throw new ServiceNotFoundException(message);
	}

	public Reserve update(ReserveDto form, Long id) {
		var reserveFound = findById(id);

		var model = reserveMapper.toModel(form);
		((Reserve) model).setId(reserveFound.getId());

		return reserveRepository.save(model);
	}

	public void deleteById(Long id) {
		var reserveFound = findById(id);

		reserveRepository.delete(reserveFound);
	}
}