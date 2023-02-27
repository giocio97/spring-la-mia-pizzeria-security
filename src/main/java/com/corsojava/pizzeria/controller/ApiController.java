package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
	@Autowired
	PizzaRepository pizzaRepository;

	@GetMapping
	public List<Pizza> index(@RequestParam(name = "keyword", required = false) String keyword) {

		if (keyword != null && !keyword.isEmpty())
			return pizzaRepository.findByNomeLike("%" + keyword + "%");

		else

			return pizzaRepository.findAll(Sort.by("nome"));
	}

	@GetMapping("{id}")
	public ResponseEntity<Pizza> detail(@PathVariable("id") Integer id) {
		Optional<Pizza> p = pizzaRepository.findById(id);
		if (p.isPresent()) {
			return new ResponseEntity<Pizza>(p.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/create")
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	@PutMapping("{id}")
	public Pizza update(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		pizza.setId(id);
		return pizzaRepository.save(pizza);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {

		pizzaRepository.deleteById(id);
	}

}
