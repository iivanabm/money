package com.money.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ItemFilter {

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateBefore;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateAfter;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDueDateBefore() {
		return dueDateBefore;
	}
	public void setDueDateBefore(LocalDate dueDateBefore) {
		this.dueDateBefore = dueDateBefore;
	}
	public LocalDate getDueDateAfter() {
		return dueDateAfter;
	}
	public void setDueDateAfter(LocalDate dueDateAfter) {
		this.dueDateAfter = dueDateAfter;
	}
	
	
}
