package com.example.examen_moviles

class Categoria (
    var nombreCategoria : String,
    var fechaCreacion : String,
    var prioridadCategoria : Int,
    var tipoCategoria: String,
    var notas: ArrayList<Notas> = arrayListOf <Notas>(),
    var categorias : ArrayList<Double>,
    var categoriaActual : Double

) {
    init {
        this.nombreCategoria; this.fechaCreacion; this.prioridadCategoria; this.tipoCategoria
    }

    constructor(
        nombreCategoria: String,
        fechaCreacion: String,
        prioridadCategoria: Int,
        tipoCategoria: String

    ) : this(
        nombreCategoria,
        fechaCreacion,
        prioridadCategoria,
        tipoCategoria,
        notas = ArrayList<Notas>(),
        categorias = ArrayList<Double>(),
        categoriaActual = 0.0
    )

    fun actualizarNotas(notas : ArrayList<Notas>){
        this.notas = notas
    }


    fun añadirNota(nota: Notas) {
        this.notas.add(nota)
    }

    fun eliminarNota(numeroNota : Int): String {
        if (numeroNota >= notas.size || numeroNota > 0) {
            val nombreNotaEliminada = this.notas.get(numeroNota - 1).nombreNota
            this.notas.removeAt(numeroNota - 1)
            return nombreNotaEliminada
        } else {
            return "Número de Nota no valido"
        }
    }



    override fun toString(): String {
        return "$nombreCategoria"
    }
}