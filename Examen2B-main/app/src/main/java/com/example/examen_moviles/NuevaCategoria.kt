package com.example.examen_moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NuevaCategoria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_categoria)
        val botonCrearCategoria = findViewById<Button>(R.id.btn_crearCategoria)
        botonCrearCategoria.setOnClickListener {
            var nombreCategoria = (findViewById<EditText>(R.id.txt_nombreCategoria)).getText().toString()
            var fechaCategoria= (findViewById<EditText>(R.id.txt_fechaCategoria)).getText().toString()
            var prioridadCategoria = Integer.parseInt((findViewById<EditText>(R.id.txt_prioridadCategoria)).getText().toString())
            var tipoCategoria = (findViewById<EditText>(R.id.txt_tipoCategoria)).getText().toString()

            crearCategoria(nombreCategoria, fechaCategoria, prioridadCategoria, tipoCategoria)

            finish()

        }
    }

    fun crearCategoria(
        nombre : String,
        fecha : String,
        prioridad : Number,
        tipo : String,
    ) {
        val db = Firebase.firestore
        val referenciaCategoria = db
            .collection("categorias")
        val datosCategoria = hashMapOf(
            "nombre" to nombre,
            "fecha" to fecha,
            "prioridad" to prioridad,
            "tipoCategoria" to tipo
        )
        referenciaCategoria
            .add(datosCategoria)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}
