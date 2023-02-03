package hotel.project.web.controllers;

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
import hotel.project.web.dtos.FlashMessage;
import hotel.project.web.dtos.RoleDto;
import hotel.project.web.services.RoleService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/role/role_list");
		modelAndView.addObject("roles", roleService.findAll());
		return modelAndView;
	}
	
	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("admin/role/role_form");
		modelAndView.addObject("insertForm", new RoleDto());
		return modelAndView;
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insertForm") RoleDto insertForm, Long id, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/role/role_form";
		}
		try {
			
			roleService.save(insertForm);
			attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Role registered with success!"));
		} catch (ValidatingException e) {
			result.addError(e.getFieldError());
			return "admin/role/role_form";
		}
		return "redirect:/admin/roles";
	}


	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("admin/role/role_form");
		modelAndView.addObject("insertForm", roleService.findById(id));
		return modelAndView;
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("insertForm") RoleDto updateForm,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/role/role_form";
		}
		try {
			roleService.update(updateForm, id);
			attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Role updated with success!"));
		} catch (ValidatingException e) {
			result.addError(e.getFieldError());
			return "admin/role/role_form";
		}
		return "redirect:/admin/roles";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attrs) {
		roleService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Role deleted with success!"));
		return "redirect:/admin/roles";
	}	
}
