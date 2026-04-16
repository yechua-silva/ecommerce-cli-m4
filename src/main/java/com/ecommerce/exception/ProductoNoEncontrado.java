package com.ecommerce.exception;

public class ProductoNoEncontrado extends TiendaException{
	public ProductoNoEncontrado(int id) {
		super("No existe procuto con ID: " + id);
	}
}
