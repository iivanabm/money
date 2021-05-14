package com.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.money.api.model.Item;
import com.money.api.repository.item.ItemRepositoryQuery;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryQuery{

}
