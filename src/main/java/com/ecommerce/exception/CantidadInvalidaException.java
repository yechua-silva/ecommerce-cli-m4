package com.ecommerce.exception;

public class CantidadInvalidaException extends TiendaException{
	public CantidadInvalidaException(int cantidad) {
		super("Cantidad invalida: " + cantidad +  ".Debe ser mayor a 0." );
	}
}
