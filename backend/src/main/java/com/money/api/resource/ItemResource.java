package com.money.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.api.event.CreatedResourceEvent;
import com.money.api.model.Item;
import com.money.api.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemResource {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Item> create(@Valid @RequestBody Item item, HttpServletResponse response){
		Item newItem = itemRepository.save(item);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, newItem.getCode()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
		
	}
	
	@GetMapping
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Item> findByCode(@PathVariable Long code){
		return itemRepository.findById(code)
				.map(item -> ResponseEntity.ok(item))
				.orElse(ResponseEntity.notFound().build());
	}
}
