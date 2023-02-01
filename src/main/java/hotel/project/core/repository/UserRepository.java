package hotel.project.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hotel.project.core.models.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
	
	User findUserById(Long id);
    
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from User u where u.id = :id")
	void removeUser(Long id);
	

    default Boolean isEmailAlreadyRegistered(User user) {
        if (user.getEmail() == null) {
            return false;
        }

        return findByEmail(user.getEmail())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }
    
    boolean existsByEmail(String email);

	List<User> findByFirstNameIgnoreCaseContaining(String query);
}
