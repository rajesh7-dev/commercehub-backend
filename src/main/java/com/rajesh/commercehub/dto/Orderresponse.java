package com.rajesh.commercehub.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orderresponse {
	
    private double totalAmount;
	
	private LocalDateTime orderDate;
	
	

}
