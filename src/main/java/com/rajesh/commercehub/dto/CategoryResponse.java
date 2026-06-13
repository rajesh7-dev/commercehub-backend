package com.rajesh.commercehub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
	
	private Long id;
	private String name;
	private String description;
	
	
	public CategoryResponse(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	
	

}
