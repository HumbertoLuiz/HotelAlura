package hotel.project.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.project.core.exceptions.ServiceNotFoundException;
import hotel.project.core.mappers.RoleMapper;
import hotel.project.core.models.Role;
import hotel.project.core.repository.RoleRepository;
import hotel.project.web.dtos.RoleDto;
import hotel.project.web.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleMapper roleMapper;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role save(RoleDto form) {
		var model = roleMapper.toModel(form);

		return roleRepository.save(model);
	}

	public Role findById(Long id) {
		var guestFound = roleRepository.findById(id);

		if (guestFound.isPresent()) {
			return guestFound.get();
		}

		var message = String.format("Service with ID %d not found", id);
		throw new ServiceNotFoundException(message);
	}

	public Role update(RoleDto form, Long id) {
		var roleFound = findById(id);

		var model = roleMapper.toModel(form);
		((Role) model).setId(roleFound.getId());

		return roleRepository.save(model);
	}

	public void deleteById(Long id) {
		var roleFound = findById(id);

		roleRepository.delete(roleFound);
	}
}