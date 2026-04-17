package com.ecommerce.service;

import com.ecommerce.model.Carrito;

public abstract class Descuento {
	protected String nombre;
	protected double porcentaje;
	
	public Descuento(String nombre, double porcentaje) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
	}
	
	public abstract boolean aplica(Carrito carrito);
	
	public double calcularDescuento(Carrito carrito) {
		if (aplica(carrito)) {
			return carrito.calcularTotalBase() * (porcentaje / 100.0);
		}
		return 0.0;
	}
	
	public abstract String getCondicion();
	
	// getters
	public String getNombre() {
		return nombre;
	}
	
	public double getPorcentaje() {
		return porcentaje;
	}
}
