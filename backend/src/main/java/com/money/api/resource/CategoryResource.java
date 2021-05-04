package com.money.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.money.api.model.Category;
import com.money.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Category> listAll(){
		return categoryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category newCategory = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
			.buildAndExpand(newCategory.getCode()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(newCategory);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Category> findByCode(@PathVariable Long code) {
		return this.categoryRepository.findById(code)
				.map(category -> ResponseEntity.ok(category))
				.orElse(ResponseEntity.notFound().build());
	}
}
