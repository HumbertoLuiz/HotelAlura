package hotel.project.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotel.project.web.dtos.FlashMessage;
import hotel.project.web.dtos.GuestDto;
import hotel.project.web.services.GuestService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;

	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/guest/guest_list");

		modelAndView.addObject("guests", guestService.findAll());

		return modelAndView;
	}

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/guest/guest_form");

		modelAndView.addObject("guestForm", new GuestDto());

		return modelAndView;
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("guestForm") GuestDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/guest/guest_form";
		}

		guestService.save(form);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guest registered with success!"));

		return "redirect:/admin/guests";
	}

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/guest/guest_form");

		modelAndView.addObject("guestForm", guestService.findById(id));

		return modelAndView;
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("guestForm") GuestDto form, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/guest/guest_form";
		}

		guestService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guest edited with success!"));

		return "redirect:/admin/guests";
	}

	@GetMapping("/{id}/delete")
	public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
		guestService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Guest deleted with success!"));

		return "redirect:/admin/guests";
	}
}
