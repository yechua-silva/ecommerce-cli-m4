package com.ecommerce.service;

import java.util.*;

import com.ecommerce.exception.ProductoNoEncontradoException;
import com.ecommerce.model.Producto;

public class Catalogo {
	private Map<Integer, Producto> productos = new HashMap<>();
	private int nextId = 1;
	
	// agregar
	public void agregarProducto(String nombre, String categoria, double precio) {
		Producto producto = new Producto(nextId++, nombre, categoria, precio);
		productos.put(producto.getId(), producto);
	}
	
	// listar por nombre
	public List<Producto> listarTodos() {
		List<Producto> lista = new ArrayList<>(productos.values());
		lista.sort(Producto.POR_NOMBRE);
		return lista;
	}
	
	// listar por comparador especifico
	public List<Producto> listarTodos(Comparator<Producto> comparador) {
		List<Producto> lista = new ArrayList<>(productos.values());
		lista.sort(comparador);
		return lista;
	}
	
	
	// buscar por id
	public Optional<Producto> buscarPorId(int id) {
		return Optional.ofNullable(productos.get(id));
	}
	
	// buscar por nombre
	public List<Producto> buscarPorNombre(String texto) {
		List<Producto> resultado = new ArrayList<>();
		String textoLower = texto.toLowerCase();
		for (Producto producto : productos.values()) {
			if (producto.getNombre().toLowerCase().contains(textoLower)) {
				resultado.add(producto);
			}
		}
		resultado.sort(Producto.POR_NOMBRE);
		return resultado;
	}
	
	// buscar por categoria
	public List<Producto> buscarPorCategoria(String categoria) {
		List<Producto> resultado = new ArrayList<>();
		for(Producto producto : productos.values()) {
			if(producto.getCategoria().equalsIgnoreCase(categoria)) {
				resultado.add(producto);
			}
		}
		resultado.sort(Producto.POR_NOMBRE);
		return resultado;
	}
	
	// editar producto
	public void editarProducto(int id, String nuevoNombre, String nuevaCategoria, double nuevoPrecio) throws ProductoNoEncontradoException {
		Optional<Producto> opt = buscarPorId(id);
		if(opt.isPresent()) {
			Producto producto = opt.get();
			producto.setNombre(nuevoNombre);
			producto.setCategoria(nuevaCategoria);
			producto.setPrecio(nuevoPrecio);
		} else {
			throw new ProductoNoEncontradoException(id);
		}
	}
	
	// eliminar producto
	public boolean eliminarProductos(int id) {
		if (productos.containsKey(id)) {
			productos.remove(id);
			return true;
		}
		return false;
	}
	
	// obtener categorias
	public Set<String> obtenerCategorias() {
		Set<String> categorias = new HashSet<>();
		for (Producto producto : productos.values()) {
			categorias.add(producto.getCategoria());
		}
		return categorias;
	}
}
