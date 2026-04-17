package com.ecommerce.service;

import com.ecommerce.model.Carrito;

public class DescuentoPorCategoria extends Descuento {
	private String categoria;
	
	public DescuentoPorCategoria(String categoria, double porcentaje) {
		super("Descuento por categoria", porcentaje);
		this.categoria = categoria;
	}
	
	@Override
	public boolean aplica(Carrito carrito) {
		for(Carrito.ItemCarrito item : carrito.getItems()) {
			if (item.getProducto().getCategoria().equalsIgnoreCase(categoria)) {
				return true;
			}
		}
		return false;
	}
	
    @Override
    public String getCondicion() {
        return String.format("Contiene productos de '%s' → %.1f%% de descuento", categoria, porcentaje);
    }
}
