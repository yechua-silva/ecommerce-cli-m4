# E-commerce CLI - Módulo 4

Aplicación de consola en Java para la gestión de un catálogo de productos, carrito de compras y aplicación automática de descuentos. Desarrollada como parte del Módulo 4 del curso de Java Backend.

## 📁 Repositorio GitHub

🔗 https://github.com/yechua-silva/ecommerce-cli-m4

## 🛠 Tecnologías utilizadas

- Java 17+
- JUnit 5 (pruebas unitarias)
- Eclipse IDE (opcional)
- Git / GitHub

## 📂 Estructura del proyecto

```
ecommerce-cli-m4/
├── src/
│   └── main/java/com/ecommerce/
│       ├── model/           → Producto, Carrito, Orden, ItemCarrito
│       ├── service/         → Catalogo, TiendaService, Descuento (abstract), DescuentoPorMonto, DescuentoPorCategoria
│       ├── ui/              → Consola (menús y entrada/salida)
│       └── exception/       → TiendaException, CantidadInvalidaException, ProductoNoEncontradoException
├── test/
│   └── java/com/ecommerce/  → Pruebas unitarias (CarritoTest, DescuentoTest, etc.)
├── README.md
└── .gitignore
```

## ✅ Funcionalidades implementadas

### Menú principal

- **ADMIN**: Gestión completa de productos (CRUD)
- **USUARIO**: Navegación del catálogo, manejo de carrito y confirmación de compra con descuentos automáticos
- **Salir**

### Módulo ADMIN

- Listar productos (con ordenamiento por nombre o precio)
- Buscar productos por nombre o categoría
- Crear nuevo producto (ID único, nombre, categoría, precio > 0)
- Editar producto (nombre, categoría, precio)
- Eliminar producto (con confirmación)

### Módulo USUARIO

- Listar / Buscar productos (mismos filtros que ADMIN)
- Agregar producto al carrito (por ID y cantidad > 0)
- Quitar producto del carrito (por ID)
- Ver carrito (ítems, subtotales, total base)
- Ver descuentos activos (reglas y condiciones)
- Confirmar compra:
  - Cálculo de total base
  - Aplicación automática de todas las reglas de descuento que correspondan
  - Muestra detalle de descuentos aplicados y total final
  - Creación de orden en memoria y vaciado del carrito

### Reglas de descuento implementadas

1. **Descuento por monto**: 10% si el total base supera $50.000
2. **Descuento por categoría**: 5% adicional si el carrito contiene productos de la categoría "Suplementación"

Ambos descuentos se acumulan si se cumplen las condiciones.

## ▶️ Instrucciones de ejecución

### Requisitos previos

- Tener instalado **Java JDK 17** o superior.
- (Opcional) Eclipse IDE o IntelliJ IDEA.

### Compilación y ejecución manual (terminal)

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/ecommerce-cli-m4.git
cd ecommerce-cli-m4

# Compilar fuentes
javac -d bin src/main/java/com/ecommerce/**/*.java

# Ejecutar aplicación
java -cp bin com.ecommerce.ui.Consola
```

### Ejecución en Eclipse

1. Importar el proyecto: `File > Import > Existing Projects into Workspace`.
2. Seleccionar la carpeta del proyecto clonado.
3. Ejecutar la clase `Consola.java` como `Java Application`.

## 🧪 Pruebas unitarias

Se incluyen **3 pruebas unitarias** con JUnit 5:

| Prueba                          | Descripción                                                                |
| ------------------------------- | -------------------------------------------------------------------------- |
| `testTotalBaseCarrito`          | Verifica el cálculo correcto del total base del carrito                    |
| `testCantidadInvalidaException` | Comprueba que se lanza la excepción personalizada al agregar cantidad <= 0 |
| `testAplicacionDescuentos`      | Valida que los descuentos se aplican correctamente según reglas definidas  |

Para ejecutar las pruebas en Eclipse:

- Clic derecho sobre la carpeta `test` → `Run As > JUnit Test`.

## 📋 Ejemplo de uso (flujo de compra)

```
=== MENÚ PRINCIPAL ===
1) ADMIN
2) USUARIO
0) Salir
Opción: 2

--- MENÚ USUARIO ---
1) Ver productos
2) Buscar producto
3) Agregar al carrito
4) Quitar del carrito
5) Ver carrito
6) Ver descuentos activos
7) Confirmar compra
0) Volver
Opción: 1

--- LISTA DE PRODUCTOS ---
[1] Titan Strength Pack - $85000.0 (Packs de Entrenamiento)
[2] Whey Protein Isolate (1kg) - $32000.0 (Suplementación)
[3] Creatina Monohidratada - $25000.0 (Suplementación)

Opción: 3
Ingrese ID del producto: 2
Ingrese cantidad: 2
✅ Producto agregado.

Opción: 5
--- CARRITO ---
Whey Protein Isolate (1kg) x 2 = $64000.0
TOTAL BASE: $64000.0

Opción: 6
--- DESCUENTOS ACTIVOS ---
1) Descuento por monto: 10% si compra >= $50000.0
2) Descuento por categoría: 5% si incluye productos de 'Suplementación'

Opción: 7
🛒 Confirmando compra...
Subtotal: $64000.0
Descuentos aplicados:
  - Descuento por monto (10%): -$6400.0
  - Descuento por categoría (5%): -$3200.0
TOTAL FINAL: $54400.0
¿Desea confirmar? (S/N): S
✅ Compra realizada con éxito. Orden #1001 creada.
```

## ✍️ Autor

- Nombre: Yechua Silva
- GitHub: https://github.com/yechua-silva

---

_Proyecto desarrollado como parte del Módulo 4 – Ecommerce CLI._
