package hotel.project.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import hotel.project.core.enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Reserve implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(nullable = false)
	private Date checkin;
	

	@Column(nullable = false)
	private Date checkout;
    
	
    @Column(nullable = false)
	private BigDecimal price;
    
    @Column(length = 11, nullable = false)
    @Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
    
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guests;
	
}
