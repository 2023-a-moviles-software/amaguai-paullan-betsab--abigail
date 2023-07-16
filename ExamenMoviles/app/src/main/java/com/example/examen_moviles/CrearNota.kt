package com.example.examen_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearNota : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nota)
        var objetoIntent : Intent = intent
        val idCategoria = objetoIntent.getIntExtra("idCategoriaNota",10)
        val botonCrearNotaCategoria = findViewById<Button>(R.id.btn_crearNotaCategoria)

        botonCrearNotaCategoria.setOnClickListener {
            var nombreNota = findViewById<EditText>(R.id.txt_nombreFruta).text.toString()
            var numNota = (findViewById<EditText>(R.id.txt_precio).text.toString()).toDouble()
            var prioridadNota = Integer.parseInt(findViewById<EditText>(R.id.txt_cantidad).text.toString())
            var fechaNota = findViewById<EditText>(R.id.txt_familiaFruta).text.toString()

            arreglo[idCategoria].notas.add(Notas(nombreNota,numNota,prioridadNota,fechaNota))

            finish()
        }


    }
}