package com.example.examen_moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevaCategoria : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_categoria)
        val botonCrearCategoria = findViewById<Button>(R.id.btn_crearCategoria)
        botonCrearCategoria.setOnClickListener {
            var nombreCategoria = (findViewById<EditText>(R.id.txt_nombreCategoria)).getText().toString()
            var fechaCategoria= (findViewById<EditText>(R.id.txt_tipoCategoria)).getText().toString()
            var prioridadCategoria = Integer.parseInt((findViewById<EditText>(R.id.txt_fechaCategoria)).getText().toString())
            var tipoCategoria = (findViewById<EditText>(R.id.txt_prioridadCategoria)).getText().toString()

            arreglo.add(Categoria(nombreCategoria,fechaCategoria,prioridadCategoria,tipoCategoria))

            finish()

        }
    }
}
