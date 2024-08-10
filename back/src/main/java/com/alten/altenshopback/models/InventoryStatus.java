package com.alten.altenshopback.models;

public enum InventoryStatus {
	INSTOCK("INSTOCK"),
	LOWSTOCK("LOWSTOCK"),
	OUTOFSTOCK("OUTOFSTOCK");

	public final String value;

	private InventoryStatus(String value) {
        this.value = value;
    }
}
