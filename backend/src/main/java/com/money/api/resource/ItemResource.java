package com.money.api.resource;

import java.util.Arrays;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.api.event.CreatedResourceEvent;
import com.money.api.exceptionhandler.MoneyExceptionHandler.Error;
import com.money.api.model.Item;
import com.money.api.repository.ItemRepository;
import com.money.api.repository.filter.ItemFilter;
import com.money.api.service.ItemService;
import com.money.api.service.exception.InactivePersonException;

@RestController
@RequestMapping("/items")
public class ItemResource {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<Item> create(@Valid @RequestBody Item item, HttpServletResponse response){
		Item newItem = itemService.saveItem(item);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, newItem.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
		
	}
	
	@GetMapping
	public List<Item> searchItems(ItemFilter itemFilter){
		return itemRepository.filter(itemFilter);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Item> findByCode(@PathVariable Long code){
		return itemRepository.findById(code)
				.map(item -> ResponseEntity.ok(item))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ExceptionHandler({InactivePersonException.class})
	public ResponseEntity<Object> handleInactivePersonException(InactivePersonException ex){
		String userMessage = messageSource.getMessage("person.inactive", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		
		return ResponseEntity.badRequest().body(errors);
	}
	
}
