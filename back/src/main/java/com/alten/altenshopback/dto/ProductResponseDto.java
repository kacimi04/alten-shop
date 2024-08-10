package com.alten.altenshopback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
	private Integer id;
	private String code;
	private String name;
	private String description;
	private String image;
	private Double price;
	private String quantity;
	private String category;
	private String inventoryStatus;
	private Integer rating;
}
