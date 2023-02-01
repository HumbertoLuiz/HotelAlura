package hotel.project.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AuthController {

	@GetMapping("/login")
	public String index() {
		return "admin/auth/login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "admin/auth/home";
	}

    
}