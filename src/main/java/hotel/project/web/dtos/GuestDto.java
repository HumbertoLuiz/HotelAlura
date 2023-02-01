package hotel.project.web.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class GuestDto {

    @NotNull
    @Size(min = 3, max = 255)
	private String firstName;

    @NotNull
    @Size(min = 3, max = 255)
	private String lastName;

    @NotNull
    @Size(min = 3, max = 255)
	private String nationality;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

    @NotNull
	private String phoneNumber;
	
}
