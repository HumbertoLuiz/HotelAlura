package hotel.project.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import hotel.project.core.exceptions.ReserveAlreadyRegisteredException;
import hotel.project.core.exceptions.ValidatingException;
import hotel.project.core.models.Reserve;
import hotel.project.core.repository.ReserveRepository;
import hotel.project.core.repository.RoleRepository;

@Component
public class ReserveValidator {

    @Autowired
    private ReserveRepository reserveRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    public void validate(Reserve reserve) {
    	validatePaymentMethod(reserve);
    }
   
	private void validatePaymentMethod(Reserve reserve) {
        if (reserveRepository.isPaymentMethodAlreadyRegistered(reserve)) {
            var message = "There is already a guest registered with this payment method";
            var fieldError = new FieldError(reserve.getClass().getName(), "paymentMethod", reserve.getPaymentMethod(), false, null, null, message);

            throw new ReserveAlreadyRegisteredException(message, fieldError);
        }

        if (reserve.getUser().isGuest(roleRepository) && reserve.getPaymentMethod() == null) {
            var message = "User type Guest must be have a payment method";
            var fieldError = new FieldError(reserve.getClass().getName(), "paymentMethod", reserve.getPaymentMethod(), false, null, null, message);
            
            throw new ValidatingException(message, fieldError);
        }
    }
}
