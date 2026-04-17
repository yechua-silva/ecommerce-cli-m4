package com.ecommerce.service;

import com.ecommerce.model.Carrito;

public class DescuentoPorMonto extends Descuento {
	private double montoMinimo;
	
	public DescuentoPorMonto(double montoMinimo, double porcentaje) {
		super("Descuento por monto", porcentaje);
		this.montoMinimo = montoMinimo;
	}
	
	@Override
	public boolean aplica(Carrito carrito) {
		return carrito.calcularTotalBase() >= montoMinimo;
	}
	
	@Override
	public String getCondicion() {
        return String.format("Compra >= $%.2f → %.1f%% de descuento", montoMinimo, porcentaje);
	}

}
