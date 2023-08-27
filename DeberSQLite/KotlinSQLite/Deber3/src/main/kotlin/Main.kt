fun main(args: Array<String>) {

//val baseDatosSQLite = SQLite()
//    baseDatosSQLite.createTable()
////    baseDatosSQLite.crearTienda(3,"TiendamaniaRemake","1724443294001",983359387,"Thomas Tapia")
//    println(baseDatosSQLite.consultarTiendas())
//    baseDatosSQLite.eliminarTiendaPorId(1)
//    println(baseDatosSQLite.consultarTiendas())
//    baseDatosSQLite.actualizarTienda(2, "PepeTienda", "1712581329001",null, null)
//    println(baseDatosSQLite.consultarTiendasPorId(2))
//
    val menu : Menu = Menu()
    var seleccion : Int = 0
    var opcion : Int = 0

    while(seleccion != 6) {
        menu.baseDatos.createTable()
        menu.mostrarMenuCategorias()
        println("Seleccione una opcion: ")
        seleccion = readln().toInt()
        when (seleccion){
            1 -> { //Mostrar Categorias
                menu.mostrarCategorias()
            }
            2 -> { //Seleccionar Tienda
                menu.mostrarCategorias()
                println("Ingrese el ID de la CATEGORIA: ")
                var id = readln().toInt()
                menu.seleccionarCategoria(id)
                while (opcion != 5){  //Menu de Notas
                    menu.mostrarMenuNotas(id)
                    println("Selecciona una opcion: ")
                    opcion = readln().toInt()
                    when (opcion){
                        1 -> { //Mostrar Notas de la Categoria
                            menu.mostrarNotas(id)
                        }
                        2 -> { //Agregar Nota a Categoria
                            println("Ingrese el NOMBRE DE LA NOTA: ")
                            var nombreNota = readln()
                            println("Ingrese la DESCRIPCION DE LA NOTA: ")
                            var descripcionNota = readln()
                            println("Ingrese la PRIORIDAD DE LA NOTA: ")
                            var prioridadNota = readln().toInt()
                            menu.agregarNota(id, nombreNota, descripcionNota, prioridadNota)
                        }
                        3 -> {
                            menu.mostrarNotas(id)
                            println("Ingrese el ID de la NOTA a ELIMINAR: ")
                            var idNota = readln().toInt()
                            menu.eliminarNota(id, idNota)
                        }
                        4 -> {
                            menu.mostrarNotas(id)
                            println("Ingrese el ID de la NOTA a MODIFICAR: ")
                            var idNota = readln().toInt()
                            var nota = menu.seleccionarNota(id, idNota)
                            println("\n")
                            println("**EN CASO DE NO ALTERAR UN CAMPO DEJE EN BLANCO**")
                            println("Ingrese el NOMBRE DE LA NOTA: ")
                            var nombreNota: String? = readln()
                            if (nombreNota == ""){
                                nombreNota = nota?.nombreNota
                            }
                            println("Ingrese la DESCRIPCION de la NOTA: ")
                            var descripcionNota : String? = readln()
                            if (descripcionNota == ""){
                                descripcionNota = nota?.descripcionNota
                            }
                            println("Ingrese la PRIORIDAD de la NOTA: ")
                            var prioridadNota : String? = readln()
                            var prioridadNotaInt : Int?
                            if (prioridadNota == ""){
                                prioridadNotaInt = nota?.prioridadNota
                            } else {
                                prioridadNotaInt = prioridadNota?.toInt()
                            }
                            menu.modificarNota(id, idNota, nombreNota, descripcionNota, prioridadNotaInt)
                        }
                    }
                }
                opcion = 0
            }
            3 -> { //Agregar Categoria
                println("Ingrese el ID de la Categoria: ")
                var id = readln().toInt()
                println("Ingrese el NOMBRE DE LA CATEGORIA: ")
                var nombreCategoria = readln()
                println("Ingrese la DESCRIPCION de la CATEGORIA: ")
                var descripcionCategoria = readln()
                println("Ingrese la PRIORIDAD de la CATEGORIA: ")
                var prioridadCategoria = readln().toInt()
                menu.agregarCategoria(id, nombreCategoria, descripcionCategoria, prioridadCategoria)
            }
            4-> { // Eliminar Categoria
                menu.mostrarCategorias()
                println("Ingrese el ID de la categoria a ELIMINAR: ")
                var id = readln().toInt()
                menu.eliminarCategoria(id)
            }
            5 -> { // Modificar Categoria
                menu.mostrarCategorias()
                println("Ingrese el ID de la categoria a MODIFICAR: ")
                var id = readln().toInt()
                var categoria = menu.seleccionarCategoria(id)
                println("\n")
                println("**EN CASO DE NO ALTERAR UN CAMPO DEJE EN BLANCO**")
                println("Ingrese el NOMBRE DE LA CATEGORIA: ")
                var nombreCategoria : String? = readln()
                if (nombreCategoria == ""){
                    nombreCategoria = categoria?.nombreCategoria
                }
                println("Ingrese la DESCRIPCION DE LA CATEGORIA: ")
                var descripcionCategoria : String? = readln()
                if (descripcionCategoria == ""){
                    descripcionCategoria = categoria?.descripcionCategoria
                }
                println("Ingrese la PRIORIDAD DE LA CATEGORIA : ")
                var prioridadCategoria : String? = readln()
                var prioridadCategoriaInt : Int?
                if (prioridadCategoria == ""){
                    prioridadCategoriaInt = categoria?.prioridadCategoria
                } else {
                    prioridadCategoriaInt = prioridadCategoria?.toInt()
                }
                menu.modificarCategoria(id, nombreCategoria, descripcionCategoria, prioridadCategoriaInt)

            }
        }
    }
}