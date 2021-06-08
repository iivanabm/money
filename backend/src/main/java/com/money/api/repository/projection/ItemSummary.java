package com.money.api.repository.projection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.money.api.model.ItemType;

public class ItemSummary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long code; 	
	private String description;
	private LocalDate dueDate;	
	private LocalDate payment_date;	
	private BigDecimal amount;
	private ItemType itemType;
	private String category;	
	private String person;
		
	public ItemSummary(Long code, String description, LocalDate dueDate, LocalDate payment_date, BigDecimal amount,
			ItemType itemType, String category, String person) {
		this.code = code;
		this.description = description;
		this.dueDate = dueDate;
		this.payment_date = payment_date;
		this.amount = amount;
		this.itemType = itemType;
		this.category = category;
		this.person = person;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	
}
