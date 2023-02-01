package hotel.project.core.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.annotation.Transient;

import hotel.project.core.repository.RoleRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	@Transient
	private String password;

	private String real_password;

	@ManyToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "user_role", 
	joinColumns = { @JoinColumn(name = "user_id" )}, 
	inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Collection<Role> roles;
	
	
	public User(String firstName, String lastName,String email, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		if (roles!=null && roles.size() > 0) {
			this.roles = new HashSet<>();
			this.roles.addAll(roles);
		}
	}

	public Boolean isAdmin(RoleRepository roleRepository) {
		return roles.equals(roleRepository.findByName("ROLE_ADMIN"));
	}

	public Boolean isUser(RoleRepository roleRepository) {
		return roles.equals(roleRepository.findByName("ROLE_USER"));
	}

	public Boolean isGuest(RoleRepository roleRepository) {
		return roles.equals(roleRepository.findByName("ROLE_GUEST"));
	}

}