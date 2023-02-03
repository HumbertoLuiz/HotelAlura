package hotel.project.web.controllers;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.project.core.exceptions.ValidatingException;
import hotel.project.core.models.Role;
import hotel.project.core.repository.RoleRepository;
import hotel.project.web.dtos.ChangePasswordForm;
import hotel.project.web.dtos.FlashMessage;
import hotel.project.web.dtos.UserDto;
import hotel.project.web.dtos.UserUpdateDto;
import hotel.project.web.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository daoRol;
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/user/user_list");
		modelAndView.addObject("users", userService.findAll());
		return modelAndView;
	}

	@ModelAttribute("roles")
	public Iterator<Role> getRoles() {
		List<Role> role = daoRol.findAll();
		Iterator<Role> getRole = role.iterator();
		return getRole;
	}
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("admin/user/user_form");
		modelAndView.addObject("insertForm", new UserDto());
		return modelAndView;
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insertForm") UserDto insertForm, Long id, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/user/user_form";
		}
		try {			
			userService.save(insertForm);
			attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User registered with success!"));
		} catch (ValidatingException e) {
			result.addError(e.getFieldError());
			return "admin/user/user_form";
		}
		return "redirect:/admin/users";
	}

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("admin/user/user_update");
		modelAndView.addObject("updateForm", userService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("updateForm") UserUpdateDto updateForm,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/user/user_update";
		}
		try {
			userService.update(updateForm, id);
			attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User updated with success!"));
		} catch (ValidatingException e) {
			result.addError(e.getFieldError());
			return "admin/user/user_update";
		}
		return "redirect:/admin/users";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		userService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "User deleted with success!"));
		return "redirect:/admin/users";
	}

	
	@GetMapping("/change-password")
	public ModelAndView changePassword() {
		var modelAndView = new ModelAndView("admin/user/change-password");
		modelAndView.addObject("changePasswordForm", new ChangePasswordForm());
		return modelAndView;
	}

	
	@PostMapping("/change-password")
	public String changePassword(@Valid @ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult result, RedirectAttributes atts, Principal principal) {
		if (result.hasErrors()) {
			return "admin/user/change-password";
		}
		try {
			userService.changePassword(changePasswordForm, principal.getName());
			atts.addFlashAttribute("alert", new FlashMessage("alert-success", "Password changed with success!"));
		} catch (ValidatingException e) {
			result.addError(e.getFieldError());
			return "admin/user/change-password";
		}
		return "redirect:/admin/users/change-password";
	}
}
