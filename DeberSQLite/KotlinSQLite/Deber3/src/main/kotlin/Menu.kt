class Menu (
    val baseDatos: SQLite = SQLite()
    ){

    fun mostrarMenuCategorias(){
        println("1. Mostrar Categorias")
        println("2. Seleccionar Categoria")
        println("3. Agregar Categoria")
        println("4. Eliminar Categoria")
        println("5. Modificar Categoria")
        println("6. Salir")
    }

    fun mostrarMenuNotas(idCategoria : Int){
        println("1. Mostrar Notas")
        println("2. Agregar Nota")
        println("3. Eliminar Nota")
        println("4. Modificar Nota")
        println("5. Salir")
    }

    fun mostrarCategorias(){
        baseDatos.consultarCategorias().forEach{ println("ID CATEGORIA:" + it.idCategoria + " NOMBRE CATEGORIA: " + it.nombreCategoria) }
        println("\n")
    }

    fun seleccionarCategoria(idCategoria : Int) : Categoria?{
        var categoria = baseDatos.consultarCategoriaPorId(idCategoria)
        println("ID CATEGORIA: " + categoria?.idCategoria)
        println("NOMBRE CATEGORIA: " + categoria?.nombreCategoria)
        println("DESCRIPCION: " + categoria?.descripcionCategoria)
        println("PRIORIDAD: " + categoria?.prioridadCategoria)
        println("\n")
        return baseDatos.consultarCategoriaPorId(idCategoria)
    }

    fun agregarCategoria(idCategoria: Int, nombreCategoria: String , descripcionCategoria : String, prioridadCategoria : Int){
        baseDatos.crearCategoria(idCategoria, nombreCategoria, descripcionCategoria, prioridadCategoria)
    }

    fun eliminarCategoria(idCategoria : Int){
        baseDatos.eliminarCategoriaPorId(idCategoria)
    }

    fun modificarCategoria(idCategoria: Int, nombreCategoria: String? , descripcionCategoria : String?, prioridadCategoria : Int?){
        baseDatos.actualizarCategoria(idCategoria, nombreCategoria,descripcionCategoria , prioridadCategoria)
    }

    fun agregarNota(idCategoria: Int, nombreNota: String, descripcionNota: String, prioridadNota: Int){
        baseDatos.crearNota(idCategoria, nombreNota, descripcionNota, prioridadNota)
    }

    fun mostrarNotas(idCategoria: Int){
        baseDatos.consultarNotas(idCategoria).forEach { println("ID NOTA: " + it.idNota + " NOMBRE NOTA: " +
                it.nombreNota + " DESCRIPCION: " + it.descripcionNota + " PRIORIDAD: " + it.prioridadNota)}
        println("\n")
    }

    fun eliminarNota(idCategoria : Int, idNota: Int) {
        baseDatos.eliminarNotaPorId(idCategoria, idNota)
    }

    fun seleccionarNota(idCategoria: Int, idNota: Int) : Nota?{
        var nota = baseDatos.consultarNotaPorId(idCategoria, idNota)
        println("ID NOTA: " + nota?.idNota)
        println("NOMBRE NOTA: " + nota?.nombreNota)
        println("DESCRIPCION: " + nota?.descripcionNota)
        println("PRIORIDAD: " + nota?.prioridadNota)
        println("\n")
        return baseDatos.consultarNotaPorId(idCategoria, idNota)
    }

    fun modificarNota(idCategoria: Int, idNota: Int, nuevoNombreNota: String?, nuevoDescripcionNota: String?, nuevaPrioridadNota: Int?){
        baseDatos.actualizarNota(idCategoria, idNota, nuevoNombreNota, nuevoDescripcionNota, nuevaPrioridadNota)
    }
}