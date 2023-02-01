package hotel.project.web.services;

import java.util.List;

import hotel.project.core.models.Role;
import hotel.project.web.dtos.RoleDto;

public interface RoleService {

	List<Role> findAll();

	Role save(RoleDto form);

	Role findById(Long id);
	
	Role update(RoleDto form, Long id);

	void deleteById(Long id);

}