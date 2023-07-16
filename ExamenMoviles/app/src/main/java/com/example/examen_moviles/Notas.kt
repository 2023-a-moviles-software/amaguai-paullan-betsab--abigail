package com.example.examen_moviles

class Notas (var nombreNota: String,
             var numNota: Double,
             var prioridadNota: Int,
             var revisada: Boolean,
             var fechaNota: String
) {
    init {
        this.nombreNota; this.numNota; this.prioridadNota; this.revisada; this.fechaNota
    }
    constructor(
        nombreNota: String,
        numNota: Double,
        prioridadNota: Int,
        fechaNota: String

    ) : this(
        nombreNota, numNota, prioridadNota, revisada = false, fechaNota
    ){
        if (prioridadNota > 0){
            this.revisada= true
        } else {
            this.revisada = false
        }
    }

    fun disminuirPrioridad(prioridadNota: Int ){
        this.numNota = this.numNota - prioridadNota
    }

    fun aumentarPrioridad(prioridadNota : Int){
        this.numNota = this.numNota+ prioridadNota
    }

    override fun toString(): String {
        return "Nota: $nombreNota  Numero de nota: $numNota  Cantidad: $prioridadNota"
    }

}