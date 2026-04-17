package com.ecommerce.ui;

import java.util.List;
import java.util.Scanner;
import com.ecommerce.exception.*;
import com.ecommerce.model.Producto;
import com.ecommerce.service.Descuento;
import com.ecommerce.service.TiendaService;

public class Consola {
    private TiendaService tienda;
    private Scanner scanner;

    public Consola() {
        this.tienda = new TiendaService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1) ADMIN");
            System.out.println("2) USUARIO");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                	menuAdmin();
                	break;
                case 2:
                	menuUsuario();
                	break;
                case 0:
                	System.out.println("¡Hasta luego!");
                	break;
                default:
                	System.out.println("Opción no válida.");
                	break;
            }
        } while (opcion != 0);
    }

    // ================== MENÚ ADMIN ==================
    private void menuAdmin() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1) Listar productos");
            System.out.println("2) Buscar producto por nombre");
            System.out.println("3) Buscar producto por categoría");
            System.out.println("4) Agregar producto");
            System.out.println("5) Editar producto");
            System.out.println("6) Eliminar producto");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	listarProductosAdmin();
                	break;
                case 2:
                	buscarPorNombre();
                	break;
                case 3:
                	buscarPorCategoria();
                	break;
                case 4:
                	agregarProducto();
                	break;
                case 5:
                	editarProducto();
                	break;
                case 6:
                	eliminarProducto();
                	break;
                case 0:
                	System.out.println("Volviendo al menú principal...");
                	break;
                default:
                	System.out.println("Opción no válida.");
                	break;
            }
        } while (opcion != 0);
    }

    // ================== MÉTODOS AUXILIARES ADMIN ==================
    private void listarProductosAdmin() {
        System.out.println("\n¿Ordenar por?");
        System.out.println("1) Nombre");
        System.out.println("2) Precio");
        System.out.print("Opción: ");
        int ord = scanner.nextInt();
        scanner.nextLine();

        List<Producto> lista;
        if (ord == 2) {
            lista = tienda.listarProductos(Producto.POR_PRECIO);
        } else {
            lista = tienda.listarProductos(); // por nombre
        }

        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Producto p : lista) {
                System.out.printf("[%d] %s - %s - $%.2f%n", 
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio());
            }
        }
    }

    private void buscarPorNombre() {
        System.out.print("Ingrese texto a buscar en nombre: ");
        String texto = scanner.nextLine();
        List<Producto> resultados = tienda.listarPorNombre(texto);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre.");
        } else {
            System.out.println("\n--- RESULTADOS ---");
            for (Producto p : resultados) {
                System.out.printf("[%d] %s - %s - $%.2f%n", 
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio());
            }
        }
    }

    private void buscarPorCategoria() {
        System.out.print("Ingrese categoría: ");
        String cat = scanner.nextLine();
        List<Producto> resultados = tienda.listarPorCategoria(cat);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos en esa categoría.");
        } else {
            System.out.println("\n--- RESULTADOS ---");
            for (Producto p : resultados) {
                System.out.printf("[%d] %s - %s - $%.2f%n", 
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio());
            }
        }
    }

    private void agregarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String cat = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        try {
            tienda.agregarProducto(nombre, cat, precio);
            System.out.println("Producto agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void editarProducto() {
        System.out.print("ID del producto a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva categoría: ");
        String cat = scanner.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        try {
            tienda.editarProducto(id, nombre, cat, precio);
            System.out.println("Producto actualizado.");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("¿Está seguro? (S/N): ");
        String conf = scanner.nextLine();
        if (conf.equalsIgnoreCase("S")) {
            if (tienda.eliminarProducto(id)) {
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("No se encontró producto con ese ID.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // ================== MENÚ USUARIO ==================
    private void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ USUARIO ---");
            System.out.println("1) Ver productos");
            System.out.println("2) Buscar producto");
            System.out.println("3) Agregar al carrito");
            System.out.println("4) Quitar del carrito");
            System.out.println("5) Ver carrito");
            System.out.println("6) Ver descuentos activos");
            System.out.println("7) Confirmar compra");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	listarProductosUsuario();
                	break;
                case 2:
                	buscarProductoUsuario();
                	break;
                case 3:
                	agregarAlCarrito();
                	break;
                case 4:
                	quitarDelCarrito();
                	break;
                case 5:
                	tienda.getCarrito().mostrarContenido();
                	break;
                case 6:
                	verDescuentos();
                	break;
                case 7:
                	confirmarCompra();
                	break;
                case 0:
                	System.out.println("Volviendo...");
                	break;
                default:
                	System.out.println("Opción no válida.");
                	break;
            }
        } while (opcion != 0);
    }

    private void listarProductosUsuario() {
        // Igual que en admin, pero sin opciones de edición
        listarProductosAdmin();
    }

    private void buscarProductoUsuario() {
        System.out.println("Buscar por:");
        System.out.println("1) Nombre");
        System.out.println("2) Categoría");
        System.out.print("Opción: ");
        int op = scanner.nextInt();
        scanner.nextLine();
        if (op == 1) {
            buscarPorNombre();
        } else {
            buscarPorCategoria();
        }
    }

    private void agregarAlCarrito() {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cant = scanner.nextInt();
        scanner.nextLine();

        try {
            tienda.agregarAlCarrito(id, cant);
            System.out.println("Producto agregado al carrito.");
        } catch (ProductoNoEncontradoException | CantidadInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void quitarDelCarrito() {
        System.out.print("ID del producto a quitar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (tienda.quitarDelCarrito(id)) {
            System.out.println("Producto quitado del carrito.");
        } else {
            System.out.println("El producto no estaba en el carrito.");
        }
    }

    private void verDescuentos() {
        System.out.println("\n--- DESCUENTOS VIGENTES ---");
        for (Descuento d : tienda.getDescuentosVigentes()) {
            System.out.println("- " + d.getNombre() + ": " + d.getCondicion());
        }
    }

    private void confirmarCompra() {
        System.out.print("¿Desea confirmar la compra? (S/N): ");
        String conf = scanner.nextLine();
        if (!conf.equalsIgnoreCase("S")) {
            System.out.println("Compra cancelada.");
            return;
        }

        try {
            tienda.confirmarCompra();
        } catch (TiendaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ================== MÉTODO MAIN ==================
    public static void main(String[] args) {
        new Consola().iniciar();
    }
}