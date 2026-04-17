package com.ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.exception.CantidadInvalidaException;
import com.ecommerce.model.Carrito;
import com.ecommerce.model.Producto;
import com.ecommerce.service.DescuentoPorCategoria;
import com.ecommerce.service.DescuentoPorMonto;

class EcommerceTest {
	private Carrito carrito;
	private Producto productoA;
	private Producto productoB;
	
	@BeforeEach
	void setUp() throws CantidadInvalidaException {
		carrito = new Carrito();
        productoA = new Producto(1, "Whey Protein", "Suplementacion", 32000);
        productoB = new Producto(2, "Mancuernas", "Implementacion", 42000);
	}
	
	
	/*
	 * ---------- TEST 1: Total base del carrito ----------
	 */
	@Test
	void totalBaseCarritoEsCalculadoCorrectamente() throws CantidadInvalidaException {
		carrito.agregar(productoA, 2);
		carrito.agregar(productoB, 1);
		
		double totalEsperado = (32000 * 2)+ (42000 * 1);
		
		assertEquals(totalEsperado, carrito.calcularTotalBase(), "El total base debe ser la suma de todos los subtotales");
	}

	
	/*
	 * ---------- TEST 2: Lanzamineto excepcion(cantidad invalida) ----------
	 */
	@Test
	void agregarCantidadNeutraLanzaExcepcion () {
		assertThrows(CantidadInvalidaException.class, () -> {
			carrito.agregar(productoA, 0);
		},  "Cantidad 0 debe lanzar CantidadInvalidaException");
	}
	
	@Test
	void agregarCantidadNegativaLanzaExcepcion () {
		assertThrows(CantidadInvalidaException.class, () -> {
			carrito.agregar(productoA, -5);
		}, "Cantidad negativa debe lanzar CantidadInvalidaException");
	}
	
	
	/*
	 * ---------- TEST 3: Correcta aplicacion de descuentos ----------
	 */
	@Test
	void descuentoPorMontoSiSuperaMontoMinimo () throws CantidadInvalidaException {
		carrito.agregar(productoA, 2);
		DescuentoPorMonto descuento = new DescuentoPorMonto(50000, 10);
		
		double montoDescuento = descuento.calcularDescuento(carrito);
		double esperado = 64000 * 0.10;
		
		assertEquals(esperado, montoDescuento, "El descuento por monto debe ser el 10% del total base");
	}
	
	@Test
	void descuentoPorMontoNoAplicaTotalInsuficiente() throws CantidadInvalidaException {
		carrito.agregar(productoA, 1);
		DescuentoPorMonto descuento = new DescuentoPorMonto(50000, 10);
		
		double montoDescuento = descuento.calcularDescuento(carrito);
		
		assertEquals(0.0, montoDescuento, "El descuento no debe aplicar si el total no supera el minimo");
	}
	
	@Test
	void descuentoPorCategoriaCuandoCategoriaEstaEnElCarrito () throws CantidadInvalidaException {
		 carrito.agregar(productoA, 1); 
	        DescuentoPorCategoria descuento = new DescuentoPorCategoria("Suplementacion", 5);

	        double montoDescuento = descuento.calcularDescuento(carrito);
	        double esperado = 32000 * 0.05; 

	        assertEquals(esperado, montoDescuento,
	                "El descuento por categoria debe aplicar cuando la categoria esta en el carrito");
	    }
	
	
	@Test
	void descuentoPorCategoriaNoAplicaCuandoNoEstaEnCarrito () throws CantidadInvalidaException {
		carrito.agregar(productoB, 1);
		DescuentoPorCategoria descuento = new DescuentoPorCategoria("Suplementacion", 5);
		
		double montoDescuento = descuento.calcularDescuento(carrito);
		
		assertEquals(0.0, montoDescuento,  "El descuento por categoria no debe aplicar si la categoria no esta en el carrito");
	}
	
	
	
}
