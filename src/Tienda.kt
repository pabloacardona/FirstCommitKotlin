

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
    println("")
    println("===============================")
    println("Productos disponibles: ${pDisponibles.size}")
    println("===============================")
    pDisponibles.forEach {
        println("")
        println("PRODUCTO DISPONIBLE: ------- ")
        println("Nombre: ${it.nombre}")
        println("Categoria: ${it.categoria}")
        println("Precio: ${it.precio}")
    }

    val pCostosos = productosCostosos(productos)
    println("")
    println("===============================")
    println("Productos costosos: ${pCostosos.size}")
    println("===============================")
    pCostosos.forEach {
        println("")
        println("PRODUCTO COSTOSO  ------- ")
        println("Nombre: ${it.nombre}")
        println("Categoria: ${it.categoria}")
        println("Precio: ${it.precio}")
    }

    // busqueda de productos por nombre,m catgoria o precio

    val porNombre = buscarProductos(productos, CampoBusqueda.NOMBRE, "gatorade")
    println("")
    println("===============================")
    println("Búsqueda por nombre 'gatorade':")
    println("===============================")
    porNombre.forEach {
        println("")
        println("---- PRODUCTO POR NOMBRE ENCONTRADO ----")
        println("Nombre: ${it.nombre}")
        println("Categoria: ${it.categoria}")
        println("Precio: ${it.precio}")
    }

    val porCategoria = buscarProductos(productos, CampoBusqueda.CATEGORIA, "granos")
    println("")
    println("===============================")
    println("Búsqueda por categoría 'granos':")
    println("===============================")
    porCategoria.forEach {
        println("")
        println("---- PRODUCTO POR CATEGORIA ENCONTRADO ----")
        println("Nombre: ${it.nombre}")
        println("Categoria: ${it.categoria}")
        println("Precio: ${it.precio}")
    }

    val porPrecio = buscarProductos(productos, CampoBusqueda.PRECIO, "10000")
    println("")
    println("===============================")
    println("Búsqueda por precio '10000':")
    println("===============================")
    porPrecio.forEach {
        println("")
        println("---- PRODUCTO POR PRECIO ENCONTRADO ----")
        println("Nombre: ${it.nombre}")
        println("Categoria: ${it.categoria}")
        println("Precio: ${it.precio}")
    }
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