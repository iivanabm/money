package com.money.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.api.model.Item;
import com.money.api.model.Person;
import com.money.api.repository.ItemRepository;
import com.money.api.repository.PersonRepository;
import com.money.api.service.exception.InactivePersonException;

@Service
public class ItemService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item saveItem(Item item) {
		validatePerson(item);
		
		return itemRepository.save(item);
	}
	
	public Item update(Long code, Item item) {
		Item savedItem = findExistentItem(code);
		if(!item.getPerson().equals(savedItem.getPerson())) {
			validatePerson(item);
		}
		
		BeanUtils.copyProperties(item, savedItem, "code");
		
		return itemRepository.save(savedItem);
	}

	private Item findExistentItem(Long code) {
		Item savedItem = itemRepository.findById(code)
				.orElseThrow(() -> new IllegalArgumentException());
		
		return savedItem;
	}

	private void validatePerson(Item item) {
		Person person = null;
		
		if(item.getPerson().getCode() != null) {
			person = personRepository.findById(item.getPerson().getCode())
					.orElseThrow(() -> new EmptyResultDataAccessException(1));
		}
		
		if(person.isInactive()) {
			throw new InactivePersonException();
		}
		
	}
	
	 
}
