import kotlinx.serialization.Serializable

@Serializable
class Nota (
    var nombreNota : String,
    var descripcionNota: String,
    var prioridadNota : Int,
    var revisada : Boolean,
    var fecha : String
){
    init {
        this.descripcionNota; this.prioridadNota; this.nombreNota; this.revisada; this.fecha
    }

    constructor(
        nombreCategoria: String,
        descripcionCategoria: String,
        prioridadCategoria: Int,
        fecha: String
    ) : this (nombreCategoria, descripcionCategoria, prioridadCategoria, revisada = false, fecha){
    }

}
