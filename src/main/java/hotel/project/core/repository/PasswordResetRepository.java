package hotel.project.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hotel.project.core.models.PasswordReset;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

    Optional<PasswordReset> findByEmail(String email);

    Optional<PasswordReset> findByToken(String token);

}
