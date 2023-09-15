package com.example.examen_moviles

class Notas (
    var idNota: String?,
    var nombreNota: String?,
    var numNota: Number?,
    var prioridadNota: Number?,
    var revisada: Boolean,
    var fechaNota: String?
) {
    init {
        this.idNota;this.nombreNota; this.numNota; this.prioridadNota; this.revisada; this.fechaNota
    }
    constructor(
        idNota: String,
        nombreNota: String?,
        numNota: Number?,
        prioridadNota: Number?,
        fechaNota: String?

    ) : this(
        idNota, nombreNota, numNota, prioridadNota, revisada = false, fechaNota
    ){

    }

    override fun toString(): String {
        return "Nota: $nombreNota  Numero de nota: $numNota  Prioridad: $prioridadNota"
    }

}