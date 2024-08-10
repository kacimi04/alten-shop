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
public class ProductPatchDto {
	private String code;
	private String name;
	private String description;
	private String image;
	private Double price;
	private Integer quantity;
	private String category;
	@InventoryStatusConstraint
	private String inventoryStatus;
	private Integer rating;
}
