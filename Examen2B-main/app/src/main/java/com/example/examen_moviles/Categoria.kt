package com.example.examen_moviles

class Categoria (
    var idCategoria: String?,
    var nombreCategoria : String?,
    var fechaCreacion : String?,
    var prioridadCategoria : Number?,
    var tipoCategoria: String?,
    var notas: ArrayList<Notas> = arrayListOf <Notas>(),
    var categorias : ArrayList<Double>,
    var categoriaActual : Double

) {
    init {
        this.nombreCategoria; this.fechaCreacion; this.prioridadCategoria; this.tipoCategoria
    }

    constructor(
        idCategoria: String,
        nombreCategoria: String?,
        fechaCreacion: String?,
        prioridadCategoria: Number?,
        tipoCategoria: String?

    ) : this(
        idCategoria,
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



    override fun toString(): String {
        return "$nombreCategoria"
    }
}