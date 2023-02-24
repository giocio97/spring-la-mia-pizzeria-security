package com.corsojava.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.pizzeria.model.Ingrediente;
import com.corsojava.pizzeria.repository.IngredienteRepository;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@GetMapping()
	public String index(Model model) {
		List<Ingrediente> elencoIngredienti = ingredienteRepository.findAll(Sort.by("nome"));
		model.addAttribute("elencoIngredienti", elencoIngredienti);
		return "/ingredienti/index";
	}

	@GetMapping("/create")
	public String create(Model model) {

		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);

		return "/ingredienti/create";

	}

	@PostMapping("/create")
	public String store(@ModelAttribute("ingrediente") Ingrediente formIngrediente, Model model) {

		ingredienteRepository.save(formIngrediente);

		return "redirect:/ingredienti";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Ingrediente ingrediente = ingredienteRepository.getReferenceById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "/ingredienti/edit";

	}

	@PostMapping("/edit/{id}")
	public String update(@ModelAttribute("ingrediente") Ingrediente formIngrediente) {

		ingredienteRepository.save(formIngrediente);
		return "redirect:/ingredienti";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		ingredienteRepository.deleteById(id);
		return "redirect:/ingredienti";

	}

}
