package com.example.examen_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearNota : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nota)
        var objetoIntent : Intent = intent
        val idCategoria = objetoIntent.getStringExtra("idCategoriaNota")
        val botonCrearNotaCategoria = findViewById<Button>(R.id.btn_crearNotaCategoria)

        botonCrearNotaCategoria.setOnClickListener {
            var nombreNota = findViewById<EditText>(R.id.txt_nombreFruta).text.toString()
            var numNota = (findViewById<EditText>(R.id.txt_precio).text.toString()).toInt()
            var prioridadNota = Integer.parseInt(findViewById<EditText>(R.id.txt_cantidad).text.toString())
            var fechaNota = findViewById<EditText>(R.id.txt_familiaFruta).text.toString()

            crearNota(idCategoria!!, nombreNota, numNota, prioridadNota, fechaNota)
//            arreglo[idCategoria].notas.add(Notas(nombreNota,numNota,prioridadNota,fechaNota))

            finish()
        }

    }

    fun crearNota(
        idCategoria : String,
        nombre : String,
        numeroNota : Number,
        prioridad : Number,
        fecha : String,
    ) {
        val db = Firebase.firestore
        val referenciaTienda = db
            .collection("categorias").document(idCategoria).collection("notas")
        val datosNota = hashMapOf(
            "nombreNota" to nombre,
            "numeroNota" to numeroNota,
            "prioridad" to prioridad,
            "fecha" to fecha,
        )
        referenciaTienda
            .add(datosNota)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}