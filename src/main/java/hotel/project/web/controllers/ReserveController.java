package hotel.project.web.controllers;

import java.util.Iterator;
import java.util.List;

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

import hotel.project.core.models.Guest;
import hotel.project.web.dtos.FlashMessage;
import hotel.project.web.dtos.ReserveDto;
import hotel.project.web.services.GuestService;
import hotel.project.web.services.ReserveService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/reserves")
public class ReserveController {

	@Autowired
	private ReserveService reserveService;

	@Autowired
	private GuestService guestService;


	@GetMapping
	public ModelAndView findAll() {
		var modelAndView = new ModelAndView("/admin/reserve/reserve_list");

		modelAndView.addObject("reserves", reserveService.findAll());

		return modelAndView;
	}

	@GetMapping("/save")
	public ModelAndView save() {
		var modelAndView = new ModelAndView("/admin/reserve/reserve_form");

		modelAndView.addObject("reserveForm", new ReserveDto());
		
		return modelAndView;
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("reserveForm") ReserveDto reserveForm, BindingResult result,
			RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/reserve/reserve_form";
		}
				
		reserveService.save(reserveForm);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service registered with success!"));

		return "redirect:/admin/reserves";
	}

	@ModelAttribute("guests")
	public Iterator<Guest> getGuests() {
		List<Guest> guest = guestService.findAll();
		Iterator<Guest> getGuest = guest.iterator();
		return getGuest;
	}

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable Long id) {
		var modelAndView = new ModelAndView("/admin/reserve/reserve_form");

		modelAndView.addObject("reserveForm", reserveService.findById(id));

		return modelAndView;
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("reserveForm") ReserveDto form,
			BindingResult result, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "/admin/reserve/reserve_form";
		}

		reserveService.update(form, id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service edited with success!"));

		return "redirect:/admin/reserves";
	}

	@GetMapping("/{id}/delete")
	public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
		reserveService.deleteById(id);
		attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Service deleted with success!"));

		return "redirect:/admin/reserves";
	}
}
