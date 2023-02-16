package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@GetMapping
	public String home() {
		return "pizze/home";
	}

	@Autowired
	PizzaRepository pizzaRepository;

	@GetMapping("/pizze")
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
}
