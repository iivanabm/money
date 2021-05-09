package com.money.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.api.model.Item;
import com.money.api.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemResource {

	@Autowired
	private ItemRepository itemRepository;
	
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
