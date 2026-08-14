

data class Producto(
    val nombre: String,    // Nombre del producto (ej: "Gatorade")
    val categoria: String, // Categoría a la que pertenece (ej: "Refrescos")
    val precio: Int,       // Precio unitario del producto
    val stock: Int,        // Cantidad disponible en inventario
)

enum class CampoBusqueda { NOMBRE, CATEGORIA, PRECIO }


fun main() {


    val productos = listOf(
        Producto("Gatorade", "Refrescos", 600000, 15),
        Producto("Libra de Lentejas", "Granos", 300000, 24),
        Producto("Libra de Jabón Fab", "Aseo", 10000, 2)
    )


    val pDisponibles = productosDisponibles(productos)
    println("Productos disponibles: ${pDisponibles.size}")
    pDisponibles.forEach { println(it) }

    val pCostosos = productosCostosos(productos)
    println("Productos costosos: ${pCostosos.size}")
    pCostosos.forEach { println(it) }

    // busqueda de productos por nombre,m catgoria o precio

    val porNombre = buscarProductos(productos, CampoBusqueda.NOMBRE, "gatorade")
    println("Búsqueda por nombre 'gatorade':")
    porNombre.forEach { println(it) }

    val porCategoria = buscarProductos(productos, CampoBusqueda.CATEGORIA, "granos")
    println("Búsqueda por categoría 'granos':")
    porCategoria.forEach { println(it) }

    val porPrecio = buscarProductos(productos, CampoBusqueda.PRECIO, "10000")
    println("Búsqueda por precio '10000':")
    porPrecio.forEach { println(it) }
}


private fun productosDisponibles(productos: List<Producto>): List<Producto> {
    return productos.filter { it.stock > 0 }
}

private fun productosCostosos(productos: List<Producto>): List<Producto> {
    return productos.filter { it.precio > 100000 }
}

private fun buscarProductos(
    productos: List<Producto>,
    campo: CampoBusqueda,
    valor: String
): List<Producto> {

    return productos.filter { producto ->

        when (campo) {

            CampoBusqueda.NOMBRE -> producto.nombre.contains(valor, ignoreCase = true)
            CampoBusqueda.CATEGORIA -> producto.categoria.contains(valor, ignoreCase = true)
            CampoBusqueda.PRECIO -> producto.precio == valor.toIntOrNull()
        }
    }
}