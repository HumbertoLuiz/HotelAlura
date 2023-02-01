package hotel.project.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hotel.project.core.enums.PaymentMethod;
import hotel.project.core.models.Reserve;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long>{
	
	Optional<Reserve> findByPaymentMethod(PaymentMethod paymentMethod);

    default Boolean isPaymentMethodAlreadyRegistered(Reserve reserve) {
        if (reserve.getPaymentMethod() == null) {
            return false;
        }

        return findByPaymentMethod(reserve.getPaymentMethod())
            .map(reserveFound -> !reserveFound.getId().equals(reserve.getId()))
            .orElse(false);
    }
	
}
