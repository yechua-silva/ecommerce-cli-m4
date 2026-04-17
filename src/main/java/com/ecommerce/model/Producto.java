package com.ecommerce.model;

import java.util.Comparator;


public class Producto {
	private final int id;
	private String nombre;
	private String categoria;
	private double precio;
	
	public Producto(int id, String nombre, String categoria, double precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("EL precio debe ser mayor a 0");
		}
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
	}
	
	// GETTERS Y SETTERS
	// id
	public int getId() {
		return id;
	}
	
	// nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// categoria
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	// precio
	public double getPrecio() {
		return precio; 
	}
	public void setPrecio(double precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio no puede ser 0 o menor");
		}
		this.precio = precio;
	}
	
	
	// COMPARADORES - constantes
	// ordenar por nombre
	public static final Comparator<Producto> POR_NOMBRE = new Comparator<Producto>() {
		@Override
		public int compare(Producto p1, Producto p2) {
			return p1.getNombre().compareToIgnoreCase(p2.getNombre());
		}
	};
	
	// precio mayor a menor
	public static final Comparator<Producto> POR_PRECIO = new Comparator<Producto>() {
		@Override
		public int compare(Producto p1, Producto p2) {
			return Double.compare(p1.getPrecio(), p2.getPrecio());
		}
	};
}
