package com.money.api.repository.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.money.api.model.Category_;
import com.money.api.model.Item;
import com.money.api.model.Item_;
import com.money.api.model.Person_;
import com.money.api.repository.filter.ItemFilter;
import com.money.api.repository.projection.ItemSummary;

public class ItemRepositoryImpl implements ItemRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Item> filter(ItemFilter itemFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);		
		Root<Item> root = criteria.from(Item.class);
				
		Predicate[] predicates= createRestrictions(itemFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Item> query = manager.createQuery(criteria);	
		addPageableRestriction(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, totalNumberOfItems(itemFilter));
	}
	
	@Override
	public Page<ItemSummary> summarize(ItemFilter itemFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ItemSummary> criteria = builder.createQuery(ItemSummary.class);
		Root<Item> root = criteria.from(Item.class);
		
		criteria.select(builder.construct(ItemSummary.class,
				root.get(Item_.code), root.get(Item_.description),
				root.get(Item_.dueDate), root.get(Item_.payment_date),
				root.get(Item_.amount), root.get(Item_.itemType),
				root.get(Item_.category).get(Category_.name),
				root.get(Item_.person).get(Person_.name)));
		
		Predicate[] predicates= createRestrictions(itemFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ItemSummary> query = manager.createQuery(criteria);	
		addPageableRestriction(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, totalNumberOfItems(itemFilter));
	}

	private Predicate[] createRestrictions(ItemFilter itemFilter, CriteriaBuilder builder, Root<Item> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(itemFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Item_.description)), "%" + itemFilter.getDescription().toLowerCase() + "%"));
		}
		
		if(itemFilter.getDueDateBefore() != null) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.get(Item_.dueDate), itemFilter.getDueDateBefore()));
		}
		
		if(itemFilter.getDueDateAfter() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Item_.dueDate), itemFilter.getDueDateAfter()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPageableRestriction(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalItemsPerPage = pageable.getPageSize();
		int firstItemOfPage = currentPage * totalItemsPerPage;
		
		query.setFirstResult(firstItemOfPage);
		query.setMaxResults(totalItemsPerPage);
	}
	
	private Long totalNumberOfItems(ItemFilter itemFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Item> root = criteria.from(Item.class);
		
		Predicate[] predicates = createRestrictions(itemFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
