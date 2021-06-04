package com.money.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.api.event.CreatedResourceEvent;
import com.money.api.model.Category;
import com.money.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_ITEM') and #oauth2.hasScope('read')")
	public List<Category> listAll(){
		return categoryRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_ITEM') and #oauth2.hasScope('write')")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category newCategory = categoryRepository.save(category);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, newCategory.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
	}
	
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_ITEM') and #oauth2.hasScope('read')")
	public ResponseEntity<Category> findByCode(@PathVariable Long code) {
		return this.categoryRepository.findById(code)
				.map(category -> ResponseEntity.ok(category))
				.orElse(ResponseEntity.notFound().build());
	}
}
