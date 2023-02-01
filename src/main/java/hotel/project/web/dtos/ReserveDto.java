package hotel.project.web.dtos;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import hotel.project.core.enums.PaymentMethod;
import hotel.project.core.models.Guest;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ReserveDto {

//	@NotNull
//	private Long guestId;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	@FutureOrPresent
	private Date checkin;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	@FutureOrPresent
	private Date checkout;

	@NotNull
	@PositiveOrZero
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal price;

	@NotNull
	private PaymentMethod paymentMethod;

	@NotNull
	private Guest guests;

}