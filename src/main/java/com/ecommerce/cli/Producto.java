package com.ecommerce.cli;

public class Producto {
	private int id;
	private String nombre;
	private String categoria;
	private double precio;
	
	public Producto(int id, String nombre, String categoria, double precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio debe ser mayor a cero");
		}
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
	
	}

}
