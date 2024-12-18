package com.jacaranda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.model.People;
import com.jacaranda.service.PeopleService;

@Controller
public class PeopleController {

	@Autowired
	PeopleService peopleService;

	@GetMapping({"/people","/"})
	public String listPeople(Model model) {

		List<People> listPeoples = peopleService.getPeoples();
		//model.addAttribute(listPeoples);
		model.addAttribute("list",listPeoples);
		return "listPeoples";

	}

	@GetMapping("/people/del")
	public String delPeopleGet(Model model, @RequestParam Optional<Integer> id) {
		People p;
		try {
			p = peopleService.getPeople(id.orElse(0));
		} catch (Exception e) {
			return "error";
		}

		model.addAttribute("people", p);
		model.addAttribute("action", "/people/del");
		model.addAttribute("enable", false);
		model.addAttribute("button", "Borrar");

		return "templatePeople";
	}
	
	@PostMapping("/people/del")
	public String delPeoplePost(Model model, @ModelAttribute People p ) {
		
		try {
			peopleService.delPeople(p);
		} catch (Exception e) {
			return "error";
		}

		model.addAttribute("msg", "Persona borrada");
		model.addAttribute("people", p);
		model.addAttribute("action", "people/del");
		model.addAttribute("enable", false);
		model.addAttribute("button", "");

		return "templatePeople";
	}

	@GetMapping("/people/edit")
	public String editPeopleGet(Model model, @RequestParam Optional<Integer> id) {
		People p;
		try {
			p = peopleService.getPeople(id.orElse(0));
		} catch (Exception e) {
			return "error";
		}
		model.addAttribute("people", p);
		model.addAttribute("action", "/people/edit");
		model.addAttribute("enable", true);
		model.addAttribute("button", "Editar");

		return "templatePeople";
	}

	@PostMapping("/people/edit")
	public String editPeoplePost(Model model, @ModelAttribute People p) {
		try {
			p = peopleService.editPeople(p);
			model.addAttribute("msg", "La persona " + p.getName() + " se ha editado con exito");

			
		} catch (Exception e) {
			model.addAttribute("msg", "Error al editar");
		}


		model.addAttribute("people", p);
		model.addAttribute("action", "/people/edit");
		model.addAttribute("enable", false);
		model.addAttribute("editId", false);
		model.addAttribute("button", "");
		
		return "templatePeople";
	}
	
	@GetMapping("/people/add")
	public String addPeopleGet(Model model) {
		model.addAttribute("people", new People());
		model.addAttribute("action", "/people/add");
		model.addAttribute("enable", true);
		model.addAttribute("button", "Añadir");

		return "templatePeople";
	}

	@PostMapping("/people/add")
	public String addPeoplePost(Model model, @ModelAttribute People p) {

		People newPeople = peopleService.addPeople(p);
		
		if (p == null) {
			model.addAttribute("msg", "Error al añadir");
		} else
			model.addAttribute("msg", "La persona " + newPeople.getName() + " con id " + newPeople.getId()+ " se ha añadido con exito");

		model.addAttribute("people", new People());
		model.addAttribute("action", "/people/add");
		model.addAttribute("enable", true);
		model.addAttribute("button", "Añadir");
		return "templatePeople";
	}

}
