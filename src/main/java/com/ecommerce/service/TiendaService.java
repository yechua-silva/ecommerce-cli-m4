package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.*;

import com.ecommerce.exception.CantidadInvalidaException;
import com.ecommerce.exception.ProductoNoEncontradoException;
import com.ecommerce.exception.TiendaException;
import com.ecommerce.model.*;
import java.util.Optional;

public class TiendaService {
	private Catalogo catalogo;
	private Carrito carrito;
	private List<Descuento> descuentos;
	private List<Orden> ordenes;
	private int nextOrdenId;
	
	public TiendaService() {
		this.catalogo = new Catalogo();
		this.carrito = new Carrito();
		this.descuentos = new ArrayList<>();
		this.ordenes = new ArrayList<>();
		this.nextOrdenId = 1001;
		
		inicializarDatos();
		
		descuentos.add(new DescuentoPorMonto(50000, 10));
		descuentos.add(new DescuentoPorCategoria("Suplementacion", 5));
	}
	
    private void inicializarDatos() {
        catalogo.agregarProducto("Whey Protein", "Suplementacion", 32000);
        catalogo.agregarProducto("Creatina", "Suplementacion", 25000);
        catalogo.agregarProducto("Mancuernas 10kg", "Implementacion", 42000);
        catalogo.agregarProducto("Plan de Entrenamiento", "Packs", 85000);
    }
    
    
    // metodos catalogo
    public List<Producto> listarProductos() {
    	return catalogo.listarTodos();
    }
    
    public List<Producto> listarProductos(Comparator<Producto> orden) {
    	return catalogo.listarTodos(orden);
    }
    
    public List<Producto> listarPorNombre(String texto) {
    	return catalogo.buscarPorNombre(texto);
    }
    
    public List<Producto> listarPorCategoria(String categoria) {
    	return catalogo.buscarPorCategoria(categoria);
    }
    
    
    // metodos carrito
    public void agregarAlCarrito(int idProducto, int cantidad) 
            throws ProductoNoEncontradoException, CantidadInvalidaException {
        Optional<Producto> opt = catalogo.buscarPorId(idProducto);
        if (!opt.isPresent()) {
        	throw new ProductoNoEncontradoException(idProducto);
        } 
        Producto producto = opt.get();
        carrito.agregar(producto, cantidad);
    }
    
    public boolean quitarDelCarrito(int idProducto) {
    	return carrito.quitar(idProducto);
    }
    
    public Carrito getCarrito() {
    	return carrito;
    }
    
    public void mostrarCarrito() {
    	carrito.mostrarContenido();
    }
    
    
    // descuentos
    public List<Descuento> getDescuentosVigentes() {
    	return Collections.unmodifiableList(descuentos);
    }
    
    
    // confirma compra
    public Orden confirmarCompra() throws TiendaException {
    	if (carrito.estaVacio()) {
    		throw new TiendaException("No se puede confirmar compra con carrito vacio");
    	}
    	double totalBase = carrito.calcularTotalBase();
    	double totalDescuento = 0.0;
    	List<Descuento> descuentosAplicados = new ArrayList<>();
    	Map<Descuento, Double> montosDescuentos = new HashMap<>();
    	
    	for (Descuento descuento : descuentos) {
    		double monto = descuento.calcularDescuento(carrito);
    		if (monto > 0) {
    			totalDescuento += monto;
    			descuentosAplicados.add(descuento);
    			montosDescuentos.put(descuento, monto);
    		}
    	}
    	
    	double totalFinal = totalBase - totalDescuento;
    	
    	// crear orden
    	List<Carrito.ItemCarrito> itemsOrden = new ArrayList<>(carrito.getItems());
    	Orden orden = new Orden(nextOrdenId++, itemsOrden, totalBase, totalDescuento, totalFinal, LocalDateTime.now());
    	ordenes.add(orden);
    	
    	carrito.vaciar();
    	
    	// mostrar detalle 
    	System.out.println("=== COMPRA CONFIRMADA ===");
    	System.out.println("Subtotal: $" + totalBase);
    	if (!descuentosAplicados.isEmpty()) {
    	    System.out.println("Descuentos aplicados:");
    	    for (Descuento descuento : descuentosAplicados) {
    	        System.out.println(" - " + descuento.getNombre() + ": -$" + montosDescuentos.get(descuento));
    	    }
    	}
    	System.out.println("TOTAL FINAL: $" + totalFinal);
    	System.out.println("Orden #" + orden.getId() + " creada.");

        return orden;
    }
    
    
    public void agregarProducto(String nombre, String categoria, double precio) {
    	catalogo.agregarProducto(nombre, categoria, precio);
    }
    
    public void editarProducto(int id, String nombre, String categoria, double precio) throws ProductoNoEncontradoException {
    	catalogo.editarProducto(id, nombre, categoria, precio);
    }
    
    public boolean eliminarProducto(int id) {
    	return catalogo.eliminarProductos(id);
    }
    
    public Set<String> obtenerCategorias() {
    	return catalogo.obtenerCategorias();
    }
}
