package com.myjavablog.controller;
import com.myjavablog.model.*;
import com.myjavablog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import com.myjavablog.model.Contact;
import com.myjavablog.model.Role;
import com.myjavablog.model.TypeContact;
import com.myjavablog.model.User;
import com.myjavablog.repository.UserRepository;
import com.myjavablog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/contacts/")
public class ContactController {

	private final ContactRepository contactRepository;
	@Autowired
	private UserService userService;


	@Autowired
	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}



	@RequestMapping("/order/")
	public String showOrderForm(Model model,
								@Param("busqueda") String busqueda,
								@Param("sortField") String sortField,
								@Param("sortDir") String sortDir,
								@Param("state") String state) {

		System.out.println(busqueda);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		String busquedaUpper = busqueda.toUpperCase();

		if(sortDir==null || sortDir.equals("asc")){

				switch(sortField) {
					case "name":
							model.addAttribute("contacts", contactRepository.findAllByOrderByNameDesc(busquedaUpper, state, user.getId()));
						break;
					case "surname":
						model.addAttribute("contacts", contactRepository.findAllByOrderBySurnameDesc(busquedaUpper, state, user.getId()));
						break;
					case "type":
						model.addAttribute("contacts", contactRepository.findAllByOrderByTypeDesc(busquedaUpper, state, user.getId()));
						break;
					case "description":
						model.addAttribute("contacts", contactRepository.findAllByOrderByDescriptionDesc(busquedaUpper, state, user.getId()));
						break;
					case "information":
						model.addAttribute("contacts", contactRepository.findAllByOrderByInformationDesc(busquedaUpper, state, user.getId()));
						break;
					default:
						// code block
				}
		}else{
				switch(sortField) {
					case "name":
						model.addAttribute("contacts", contactRepository.findAllByOrderByNameAsc(busquedaUpper, state, user.getId()));
						break;
					case "surname":
						model.addAttribute("contacts", contactRepository.findAllByOrderBySurnameAsc(busquedaUpper, state, user.getId()));
						break;
					case "type":
						model.addAttribute("contacts", contactRepository.findAllByOrderByTypeAsc(busquedaUpper, state, user.getId()));
						break;
					case "description":
						model.addAttribute("contacts", contactRepository.findAllByOrderByDescriptionAsc(busquedaUpper, state, user.getId()));
						break;
					case "information":
						model.addAttribute("contacts", contactRepository.findAllByOrderByInformationAsc(busquedaUpper, state, user.getId()));
						break;
					default:
						// code block
				}
		}

		model.addAttribute("busqueda", busqueda);
        model.addAttribute("state", state);
		//model.addAttribute("contacts", contactRepository.findAllByOrderByNameAsc());


		//model.addAttribute("contacts", contactRepository.findByName(keyWord.toUpperCase()));

		return "index";
	}

	@GetMapping("index")
	public String showIndexUpForm(Contact contact) {
		return "index";
	}

	@GetMapping("signup")
	public String showSignUpForm(Contact contact) {
		return "add-contact";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("contacts", contactRepository.findAllByStateOrderByNameDesc("ACTIVO", user.getId()));
		//model.addAttribute("contacts", contactRepository.findByName(keyWord.toUpperCase()));

		return "index";
	}
	@GetMapping("list2")
	public String showUpdateForm(Model model, String keyWord, String checked) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		System.out.println(keyWord);

		String state="";

		if (checked==null){
			state="ACTIVO";
		};


		//model.addAttribute("contacts", contactRepository.findAll());
		model.addAttribute("contacts", contactRepository.findByName(keyWord.toUpperCase(), state, user.getId()));
		model.addAttribute("busqueda", keyWord);
		model.addAttribute("state", state);

		return "index";
	}

	@PostMapping("add")
	public String addContact(@Valid Contact contact, BindingResult result, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		contact.setUserid(user.getId());
		contact.setNameAndSurname(contact.getName()+" "+contact.getSurname());
		if (result.hasErrors()) {
			return "add-contact";
		}
        contact.setState("ACTIVO");
		contactRepository.save(contact);
		//return "redirect:list";
		return "index";
	}

	@GetMapping("see/{id}")
	public String showSeeForm(@PathVariable("id") long id, Model model) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
		model.addAttribute("contact", contact);
		model.addAttribute("TypeContacts", TypeContact.values());
		return "see-contact";
	}


	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
		model.addAttribute("contact", contact);
		model.addAttribute("TypeContacts", TypeContact.values());
		return "update-contact";
	}

	@PostMapping("update/{id}")
	public String updateContact(@PathVariable("id") long id, @Valid Contact contact, BindingResult result,
                                Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		if (result.hasErrors()) {
			contact.setId(id);
			return "update-contact";
		}
		contact.setNameAndSurname(contact.getName()+" "+contact.getSurname());
        contact.setState("ACTIVO");
		contactRepository.save(contact);
		model.addAttribute("contacts", contactRepository.findAllByStateOrderByNameDesc("ACTIVO", user.getId()));
		return "index";
	}

	@RequestMapping("cancelar")
	public String cancelar() {

		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteContact(@PathVariable("id") long id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));

		if(contact.getState().equals("INACTIVO")){
			contact.setState("ACTIVO");
		}else{
			contact.setState("INACTIVO");
		}

		contactRepository.save(contact);
		model.addAttribute("contacts", contactRepository.findAllByStateOrderByNameDesc("ACTIVO", user.getId()));
		return "index";
	}
}
