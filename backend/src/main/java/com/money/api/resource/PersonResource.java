package com.money.api.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.money.api.event.CreatedResourceEvent;
import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;
import com.money.api.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PersonService personService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person newPerson = personRepository.save(person);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, newPerson.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}
	
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
	public ResponseEntity<Person> findByCode(@PathVariable Long code){
		Optional<Person> person = personRepository.findById(code);
		return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and #oauth2.hasScope('write')")
	public void remove(@PathVariable Long code) {
		personRepository.deleteById(code);
	}
	
	@PutMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasSCope('write')")
	public ResponseEntity<Person> update(@PathVariable Long code, @Valid @RequestBody Person person){
		Person savedPerson = personService.update(code, person);
		return ResponseEntity.ok(savedPerson);
	}

	@PutMapping("/{code}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
	public void updateActiveProperty(@PathVariable Long code, @RequestBody Boolean active) {
		personService.updateActiveProperty(code, active);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON')")
	public Page<Person> search(@RequestParam(required = false, defaultValue = "%") String name, Pageable pageable){
		return personRepository.findByNameContaining(name, pageable);
	}
	
}
