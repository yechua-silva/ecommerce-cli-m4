package com.ecommerce.model;

public class Carrito {
	public static class ItemCarrito {
		private Producto producto;
		private int cantidad;
		
		public ItemCarrito(Producto producto, int cantidad) {
			this.producto = producto;
			this.cantidad = cantidad;
		}
	}
}
