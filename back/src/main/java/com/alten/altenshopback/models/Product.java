package com.alten.altenshopback.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alten_shop_sequence")
	@SequenceGenerator(name = "alten_shop_sequence", sequenceName = "alten_shop_sequence", allocationSize = 1)
	private Integer id;
	@Column(name = "CODE", nullable = false)
	private String code;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	@Column(name = "IMAGE", nullable = true)
	private String image;
	@Column(name = "PRICE", nullable = false)
	private Double price;
	@Column(name = "RATING")
	private Integer rating;
	@Column(name = "QUANTITY")
	private Integer quantity;
	@OneToOne
	private Category category;
	@Enumerated(EnumType.STRING)
	@Column(name = "INVENTORY_STATUS")
	private InventoryStatus inventoryStatus;
}
