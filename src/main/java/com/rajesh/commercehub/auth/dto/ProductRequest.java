package com.rajesh.commercehub.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
	
	@NotBlank(message = "Product name required")
    private String name;
	
	@NotBlank(message = "Description required")
    private String description;
	
	@NotNull(message = "Price required")
    private Double price;
	
	@NotNull(message = "Quantity required")
    private Integer quantity;


}
