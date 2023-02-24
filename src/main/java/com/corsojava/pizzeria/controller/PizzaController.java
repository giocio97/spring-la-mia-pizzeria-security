package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Ingrediente;
import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.IngredienteRepository;
import com.corsojava.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

	@Autowired
	PizzaRepository pizzaRepository;

	@Autowired
	IngredienteRepository ingredienteRepository;

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		List<Pizza> elencoPizze;

		if (keyword == null) {
			elencoPizze = pizzaRepository.findAll();
		} else {
			elencoPizze = pizzaRepository.findByNomeLike("%" + keyword + "%");
			;
		}

		model.addAttribute("elencoPizze", elencoPizze);
		return "pizze/index";

	}

	@GetMapping("/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Optional<Pizza> p = pizzaRepository.findById(id);
		if (p.isPresent()) {
			model.addAttribute("pizza", p.get());
		} else {
			return "pizze/index";
		}

		return "pizze/detail";
	}

	@GetMapping("/create")

	public String create(Model model) {
		Pizza pizza = new Pizza();
		List<Ingrediente> listaIngredienti = ingredienteRepository.findAll(Sort.by("nome"));
		model.addAttribute("listaIngredienti", listaIngredienti);
		model.addAttribute("pizza", pizza);

		return "pizze/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return "pizze/create";

		pizzaRepository.save(formPizza);
		return "redirect:/pizze";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Pizza pizza;

		pizza = pizzaRepository.getReferenceById(id);
		List<Ingrediente> listaIngredienti = ingredienteRepository.findAll(Sort.by("nome"));
		model.addAttribute("listaIngredienti", listaIngredienti);
		model.addAttribute("pizza", pizza);
		return "pizze/edit";

	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "pizze/create";

		pizzaRepository.save(formPizza);
		return "redirect:/pizze";

	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		pizzaRepository.deleteById(id);
		return "redirect:/pizze";

	}

}
