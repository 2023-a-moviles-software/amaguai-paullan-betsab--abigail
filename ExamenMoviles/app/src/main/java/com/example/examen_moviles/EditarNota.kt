package com.example.examen_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarNota : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)
        val objetoIntent : Intent = intent
        val idCategoria= objetoIntent.getIntExtra("idCategoria",10)
        val idNota = objetoIntent.getIntExtra("idNota",10)

        findViewById<TextView>(R.id.tv_nombreNotaEdit).setText(arreglo[idCategoria].notas[idNota].nombreNota)
        findViewById<EditText>(R.id.txt_nombreNotaEdit).setText((arreglo[idCategoria].notas[idNota].nombreNota).toString())
        findViewById<EditText>(R.id.txt_prioridadNotaEdit).setText((arreglo[idCategoria].notas[idNota].numNota).toString())
        findViewById<EditText>(R.id.txt_numNotaEdit).setText((arreglo[idCategoria].notas[idNota].prioridadNota).toString())
        findViewById<EditText>(R.id.txt_fechaNotaEdit).setText(arreglo[idCategoria].notas[idNota].fechaNota)

        val botonActualizarNota = findViewById<Button>(R.id.btn_actualizarNota)
        botonActualizarNota.setOnClickListener {
            var nombreNota = findViewById<EditText>(R.id.txt_nombreNotaEdit).text.toString()
            var numNota = (findViewById<EditText>(R.id.txt_numNotaEdit).text.toString()).toDouble()
            var prioridadNota = (findViewById<EditText>(R.id.txt_prioridadNotaEdit).text.toString()).toInt()
            var fechaNota = findViewById<EditText>(R.id.txt_fechaNotaEdit).text.toString()

            arreglo[idCategoria].notas[idNota].nombreNota = nombreNota
            arreglo[idCategoria].notas[idNota].numNota = numNota
            arreglo[idCategoria].notas[idNota].prioridadNota = prioridadNota
            arreglo[idCategoria].notas[idNota].fechaNota = fechaNota



            finish()

        }

    }
}