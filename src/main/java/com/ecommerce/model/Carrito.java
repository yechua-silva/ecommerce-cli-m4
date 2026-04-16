package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.exception.CantidadInvalidaException;

public class Carrito {
	public static class ItemCarrito {
		private Producto producto;
		private int cantidad;
		
		// sub-item ItemCarrito
		public ItemCarrito(Producto producto, int cantidad) {
			this.producto = producto;
			this.cantidad = cantidad;
		}
		
		// getter y setters
		public Producto getProducto() {
			return producto;
		}
		
		public int getProductos() {
			return cantidad;
		}
		
		public void sumarCantidad(int extra) {
			if (extra <=0 ) {
				throw new IllegalArgumentException("La cantidad debe ser positiva");
			}
			this.cantidad += extra;
		}
		
		public double getSubTotal() {
			return producto.getPrecio() * cantidad;
		}
		
		@Override
		public String toString() {
			return producto.getNombre() + " x " + cantidad + " = $" + getSubTotal();
		}
	}
	
	
	private List<ItemCarrito> items;
	
	public Carrito() {
		this.items = new ArrayList<>();
	}
	
	public void agregar(Producto producto, int cantidad) throws CantidadInvalidaException {
		// validar cantidad
		if (cantidad <= 0) {
			throw new CantidadInvalidaException(cantidad);
		}
		
		// buscar producto
		for (ItemCarrito item : items) {
			if (item.getProducto().getId() == producto.getId()) {
				item.sumarCantidad(cantidad);
				return;
			}
		}
		
		// en caso de no existir
		ItemCarrito nuevoItem = new ItemCarrito(producto, cantidad);
		items.add(nuevoItem);
	}
	
	public boolean quitar(int idProducto) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProducto().getId() == idProducto) {
				items.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public double calcularTotalBase() {
		double total = 0.0;
		for(ItemCarrito item : items) {
			total += item.getSubTotal();
		}
		return total;
	}
	
	public List<ItemCarrito> getItems() {
		return new ArrayList<>(items);
	}
	
	public void vaciar() {
		items.clear();
	}
	
	public boolean estaVacio() {
		return items.isEmpty();
	}
	
	public void mostrarContenido() {
		if (items.isEmpty()) {
			System.out.println("El carrito esta vacio");
			return;
		}
		System.out.println("--- CARRITO ---");
		for(ItemCarrito item : items) {
			System.out.println(item);
		}
		System.out.println("TOTAL BASE: $" + calcularTotalBase());
	}
}
