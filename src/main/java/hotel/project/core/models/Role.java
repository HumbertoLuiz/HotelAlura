package hotel.project.core.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
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
public class Role implements GrantedAuthority{		
	
	private static final long serialVersionUID = 1L;	
	
	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 50)
	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Collection<User> users;

	@Override
	public String getAuthority() {
		return this.name;
	}
}
