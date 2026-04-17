package com.ecommerce.exception;

public class ProductoNoEncontradoException extends TiendaException {
    public ProductoNoEncontradoException(int id) {
        super("No existe producto con ID: " + id);
    }
}