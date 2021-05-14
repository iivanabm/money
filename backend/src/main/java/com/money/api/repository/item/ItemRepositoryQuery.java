package com.money.api.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.money.api.model.Item;
import com.money.api.repository.filter.ItemFilter;

public interface ItemRepositoryQuery {

	public Page<Item> filter(ItemFilter itemFilter, Pageable pageable);
}
