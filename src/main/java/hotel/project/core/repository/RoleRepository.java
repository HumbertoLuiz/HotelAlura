package hotel.project.core.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hotel.project.core.models.Role;
import jakarta.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Set<Role> findByName(String name);

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Role r where r.id = :id")
	void removeRole(Long id);

	List<Role> findByNameIgnoreCaseContaining(String name);

	Role getRoleById(String id);

	Role getRoleByName(String name);

}
