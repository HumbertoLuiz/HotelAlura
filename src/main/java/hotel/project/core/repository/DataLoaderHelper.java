//package hotel.project.core.repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import hotel.project.core.models.Role;
//import hotel.project.core.models.User;
//
////Para popular no banco de dados
//
//
//@Service
//public class DataLoaderHelper {
//	
//	public static void loadData( UserRepository daoUser, RoleRepository daoRol) {		
//
//		List<Role> roleList = new ArrayList<>();
//		roleList.add(new Role(1L, "ROLE_ADMIN", null));
//		roleList.add(new Role(2L, "ROLE_USER", null));
//		roleList.add(new Role(3L,"ROLE_GUEST", null));		
//		daoRol.saveAll(roleList);
//
//		//Exemplo para salvar o usuario com senha criptografada
//		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
//		
//		List<User> userList = new ArrayList<>();
//		userList.add(new User(1L, "Maria", "Aparecida", "maria90@test.org", passEncoder.encode("123"),  "123", daoRol.findByName("ROLE_USER")));
//		userList.add(new User(2L, "Admin", "Role-Admin", "admin@test.org", passEncoder.encode("123"), "123", daoRol.findByName("ROLE_ADMIN")));
//		userList.add(new User(3L, "Guest", "Role-Guest", "guest@test.org", passEncoder.encode("123"), "123", daoRol.findByName("ROLE_GUEST")));
//		daoUser.saveAll(userList);
//	}
//	
//	@Bean
//	public CommandLineRunner loader(UserRepository daoUser, RoleRepository daoRol) {
//		return (args) -> {
//			DataLoaderHelper.loadData(daoUser, daoRol);
//		};
//	}
//}
