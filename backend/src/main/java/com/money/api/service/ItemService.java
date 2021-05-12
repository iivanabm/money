package com.money.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.api.model.Item;
import com.money.api.model.Person;
import com.money.api.repository.ItemRepository;
import com.money.api.service.exception.InactivePersonException;

@Service
public class ItemService {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	public Item saveItem(Item item) {
		Person person = personService.findPersonByCode(item.getPerson().getCode());
		if(person.isInactive()) {
			throw new InactivePersonException();
		}
		
		return itemRepository.save(item);
	}
}
