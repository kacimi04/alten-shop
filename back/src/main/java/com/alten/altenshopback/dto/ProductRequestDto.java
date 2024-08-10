package com.alten.altenshopback.dto;

import com.alten.altenshopback.validators.InventoryStatusConstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
	@NotBlank
	private String code;
	@NotBlank
	private String name;
	private String description;
	@NotBlank
	private String image;
	@NotNull
	private Double price;
	@NotBlank
	private Integer quantity;
	@NotBlank
	private String category;
	@NotBlank
	@InventoryStatusConstraint
	private String inventoryStatus;
	private Integer rating;
}
