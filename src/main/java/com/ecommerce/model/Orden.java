package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

public class Orden {
	private final int id;
	private final List<Carrito.ItemCarrito> items;
	private final double totalFinal;
	private final LocalDateTime fecha;
	
	public Orden(int id, List<Carrito.ItemCarrito> items, double totalFinal, LocalDateTime fecha) {
		this.id = id;
		this.items = items;
		this.totalFinal = totalFinal;
		this.fecha = fecha;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public List<Carrito.ItemCarrito> getItems() {
		return items;
	}
	
	public double getTotalFinal() {
		return totalFinal;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
}
