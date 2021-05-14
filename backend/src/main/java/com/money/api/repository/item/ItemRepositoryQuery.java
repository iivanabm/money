package com.money.api.repository.item;

import java.util.List;

import com.money.api.model.Item;
import com.money.api.repository.filter.ItemFilter;

public interface ItemRepositoryQuery {

	public List<Item> filter(ItemFilter itemFilter);
}
