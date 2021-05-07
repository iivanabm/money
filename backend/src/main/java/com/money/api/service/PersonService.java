package com.money.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	public Person update(Long code, Person person) {
		
		Person savedPerson = findPersonByCode(code);
		BeanUtils.copyProperties(person, savedPerson, "code");
		
		return personRepository.save(savedPerson);
	}
	
	public void updateActiveProperty(Long code, Boolean active) {
		Person savedPerson = findPersonByCode(code);
		savedPerson.setActive(active);
		personRepository.save(savedPerson);
	}
	
	
	private Person findPersonByCode(Long code) {
		return this.personRepository.findById(code)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}
