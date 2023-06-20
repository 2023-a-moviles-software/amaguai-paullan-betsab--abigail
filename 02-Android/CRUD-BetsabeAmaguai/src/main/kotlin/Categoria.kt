import kotlinx.serialization.Serializable
// La clase puede ser convertida a un formato de datos serializado como JSON
@Serializable
class Categoria(
    val nombreCategoria : String,
    val descripcionCategoria : String,
    val prioridadCategoria : Int,
    val tipoCategoria : String,
    var notas : ArrayList<Nota>
)
{
    //Se inicializa la propiedad con el valor de los atributos
    init {
        this.nombreCategoria;this.descripcionCategoria;this.prioridadCategoria;this.tipoCategoria;this.notas
    }
    constructor(
        nombreCategoria: String,
        descripcionCategoria: String,
        prioridadCategoria: Int,
        tipoCategoria: String
    ) : this (
        nombreCategoria,
        descripcionCategoria,
        prioridadCategoria,
        tipoCategoria,
        notas = ArrayList<Nota>()
    ){

    }

    fun agregarNota(nota:Nota){
        this.notas.add(nota)
    }

    fun eliminarNota(indexNota : Int) : String{
        if (indexNota >= notas.size ) {
            val nombreFrutaEliminada = this.notas.get(indexNota - 1).nombreNota
            //RemoveAt(indexNota), se eliminará el elemento en la posición especificada por el índice.
            this.notas.removeAt(indexNota - 1)
            return nombreFrutaEliminada
        } else {
            return "Número de nota no es valido"
        }
    }

    fun cambiarPrioridadNota(indexNota : Int, nuevaPrioridad : Int): String {
        if (indexNota >= notas.size || indexNota> 0 ) {
            if (nuevaPrioridad >= 0) {
                this.notas.get(indexNota - 1).prioridadNota = nuevaPrioridad
                return ("Se cambio la prioridad de ${notas.get(indexNota - 1).nombreNota
                } a $nuevaPrioridad ") } else {
                return "El valor no es permitido"
            }
        } else {
            return "Número de nota no es valido"
        }
    }

}