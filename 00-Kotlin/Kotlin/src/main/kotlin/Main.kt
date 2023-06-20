import java.util.ArrayList
import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    //INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Betsabe";
    //inmutable = "Abigail"

    //Mutables ( Re asignar)
    var mutable: String = "Abigail";
    mutable = "Betsabe";

    // Solo para reasignar la variable se usa el var
    // val > var
    // Duck Typing -> Si es que suena como un patosera un pato
    var ejemploVariable = "Betsabe Amaguai"
    //Debemos asignar que tipo de variable es
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo;

    //Variables primitiva
    val nombreProfesor: String = "Betsabe Amaguai"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()

    //Condicionales
    //SWITCH -> No existe en kotlin
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        //Todo esto se esjecutara cuando la variable sea C, s
        ("C") -> {
            println("Casado")
        }

        "S" -> {
            println("Soltero")
        }

        else -> {
            println("No sabemos")
        }
    }
    //Reasignación en una sola linea
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"


    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    //Escribo el nombre del parametro y le igualo el valor
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados hasat finalizar

    val sumaUno = Suma(1,1) //llamada desde el constructor primario
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS
    //Tipos de arreglos
    //Arreglo Estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    //Es una lista
    println(arregloDinamico)
    //OPERADORES -> Sirven tanto para arreglos estáticos y dinámicos

    //FOR EACH -:nit
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        //Numero de veces quese imprimira el valor es dependiente del entero
        .forEach{ valorActual:Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloDinamico.forEach{ println(it) } // it (en ingles eso) significa el elemento interado

    arregloEstatico
        //Itere y me regrese valor de indice y valor actual
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Muta el arreglo(Modificar o cambiar el arreglo)
    //1) Enviemos el nuevo valor de la iteracion
    // 2) Nos deveulve en un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            //Devuelve alg a la palbra map
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map {it + 15}

    //Filter -> Filtrar el arreglo
    //Recibe expresión o condición
    //1) Devolver una expresión (TRUE or False)
    //2)Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco:Boolean = valorActual > 5 //Expresión de la condición
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloEstatico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //Operadores de logica matemática
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny:Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual >5 )
        }
    println(respuestaAny) //TRUE

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5 )
        }
    println(respuestaAll) // FALSE

    //REDUCE -: Valor acumulado
    //Valor acumulado = 0 (Siempre 0 en lenguaje kotlin)
    // [1, 2,3 4, 5] -> Sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza +1 = 0 + 1 = 1 ->Iteracion 1
    //valorIteracion1 = valorEmpieza +2 = 1 + 2 = 3 ->Iteracion 2
    //valorIteracion1 = valorEmpieza +3 = 3 + 3 = 6 ->Iteracion 3
    //valorIteracion1 = valorEmpieza +4 = 6 + 4 = 10 ->Iteracion 4
    //valorIteracion1 = valorEmpieza +5 = 10 + 5 = 15 ->Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado:Int, valorActual: Int ->
            return@reduce (acumulado +valorActual) //-> Logica negocio
        }
    println(respuestaReduce) //78
}

//Funciones
//Cuando no se devuelvenada es void -> unit es el reemplazo

fun imprimirNombre(nombre: String): Unit{
    //${} para colocar una variable directa sin concatenar
    println("Nombre : ${nombre}") //template strings
}
fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opncional(defecto) , no podra ser nullable
    bonoEspecial: Double? = null, //Opcion null -> nullable

): Double{
    //?Nos dice que podria ser nula y debemos realizar una revisión
    //Int -> Int?(nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}

//Clase abstracta
abstract class NumerosJava {
    //Modificadores de acceso
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) { //Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros( //Constructor PRIMARIO
    //Ejemplo:
    // uno : Int, (Parametro (sin modificador de acceso))
    //private var uno: Int, //ropiedad Publica Cllase numero.uno
    //var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
    //public var uno: Int,
    protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos
){
    //var cedula: string = "" (public es por defecto)
    //private valorCalculado: Int = 0 (private)
    init { //bloque constructor primario
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializamos")

    }
}

//Nueva clase -> Suma
class Suma( //Constructor Primario Suma
    //Parametros  llamada
    unoParametro: Int,  //Parametro
    dosParametro: Int,  //Parametro
    //Herencia
): Numeros(unoParametro, dosParametro){ //Extendiendo y mandando los parametros (super)
    init{ //Bloque de código constructor primario
        this.numeroUno
        this.numeroDos
    }
    //Multiples constructores
    constructor(  //Segundo constructor
        uno: Int?,//Parametros
        //Puede ser nulo
        dos: Int //Parametros
    ):this(
        //Llamando al constructor primario
        if(uno == null) 0 else uno,
        dos
    )
    constructor(  //Tercer constructor
        uno: Int,//Parametros
        //Puede ser nulo
        dos: Int? //Parametros
    ):this(
        uno,
        //Llamando al construcutor primario
        if(dos == null) 0 else dos,
    )
    constructor(  //Cuarto constructor
        uno: Int?,//Parametros
        //Puede ser nulo
        dos: Int? //Parametros
    ):this(
        //Llamando al constructor primario
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,

        )
    //Metodo con modificador de acceso que es el público por defecto
    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total) //this.agregarHistorial(total)
        return total
    }

    companion object{ //Atributos y metodos "Compartidos", Singletons o Static de esta clase
        //Todas las instancias de esta clase comparten estos atributos y métodos
        //dentro del compain Object
        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int{
            return num * num
        }
        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }

    }
}

