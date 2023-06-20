import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner
import java.util.*

fun main(args: Array<String>) {
    var opcion : Int
    var salir : Boolean = false
    val input = Scanner(System.`in`)
    var nombreCategoria : String? = null
    var descripcionCategoria : String? = null
    var prioridadCategoria : Int? = null
    var tipoCategoria : String? = null
    var categoria : Categoria? = null

    try {
        categoria = Json.decodeFromString(File("resource/categoria.json").readText())
        nombreCategoria = categoria?.nombreCategoria

    } catch (e : FileNotFoundException) {
        println("Ingrese el nombre de la categoria: ")
        nombreCategoria = input.nextLine()
        println("Ingrese la descripcion de la categoria: ")
        descripcionCategoria = input.nextLine()
        println("Ingrese la prioridad de la categoria: ")
        prioridadCategoria = input.nextLine().toInt()
        println("Ingrese el tipo de la categoria: ")
        tipoCategoria = input.nextLine()
        categoria = Categoria(nombreCategoria, descripcionCategoria, prioridadCategoria, tipoCategoria)
    }

    fun menu(){
        println("/$nombreCategoria/")
        println("1. Agregar nota")
        println("2. Borrar nota")
        println("3. Mostrar nota")
        println("4. Cambiar prioridad nota")
        println("0. Salir")
    }

    while(!salir){
        menu()
        File("resource/categoria.json").writeText(Json.encodeToString(categoria))
        opcion = input.nextLine().toInt()
        when (opcion){
            1 -> {
                println("/Añadir Nota/")
                println("Nombre de la nota: ")
                var nombreNota = input.nextLine()
                println("Descripcion nota: ")
                var descripcionNota = input.nextLine()
                println("Prioridad nota: ")
                var prioridadNota = input.nextLine().toInt()
                println("Fecha de ingreso de la nota: ")
                var fechaNota = input.nextLine()
                var nota = Nota(nombreNota,descripcionNota,prioridadNota,fechaNota)
                if (categoria != null){
                    categoria.agregarNota(nota)
                }
                println("Se añadio la nota")
            }
            2 -> {
                println("/Borrar nota/")
                if (categoria != null) {
                    var contador = 0
                    categoria.notas.forEach {contador += 1 ; println("$contador. " + it.nombreNota + " Revisada: ${it.revisada}") }
                    println("Seleccione el número de la nota: ")
                    var numeroNota = input.nextLine().toInt()
                    categoria.eliminarNota(numeroNota)
                    println("***************")
                }
            }
            3 -> {
                if(categoria?.notas?.size == 0) {
                    println("No existen notas")
                } else {
                    println("/Mostrar notas/")
                    if (categoria != null) {
                        var contador = 0
                        categoria.notas.forEach {contador += 1 ; println("$contador. " + it.nombreNota) }
                    }
                    println("Seleccionar número nota: ")
                    var numeroNota = input.nextLine().toInt()
                    println(categoria?.notas?.get(numeroNota - 1)?.nombreNota)
                    println(categoria?.notas?.get(numeroNota - 1)?.descripcionNota)
                    println(categoria?.notas?.get(numeroNota - 1)?.prioridadNota)
                    println(categoria?.notas?.get(numeroNota - 1)?.revisada)
                    println(categoria?.notas?.get(numeroNota - 1)?.fecha)
                }
                println("***************")
            }
            4 -> {
                if(categoria?.notas?.size == 0) {
                    println("No existen notas")
                } else {
                    println("/Mostrar notas/")
                    if (categoria != null) {
                        var contador = 0
                        categoria.notas.forEach {contador += 1 ; println("$contador. " + it.nombreNota) }
                    }
                    println("Seleccionar número de nota para cambiar prioridad")
                    var numeroNota = input.nextLine().toInt()
                    println("Ingrese la nueva prioridad: ")
                    var nuevaPrioridad = input.nextLine().toInt()
                    categoria?.cambiarPrioridadNota(numeroNota,nuevaPrioridad)
                }
                println("***************")
            }
            0 -> {
                salir = true
            }
        }
    }


}