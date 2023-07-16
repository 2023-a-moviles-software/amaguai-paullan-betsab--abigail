package com.example.examen_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarCategoria : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tienda)
        val objetoIntent : Intent = intent
        var idCategoria = objetoIntent.getIntExtra("idCategoria",10)
        findViewById<TextView>(R.id.tv_tituloNombreTienda).setText(arreglo.get(idCategoria).nombreCategoria)
        findViewById<EditText>(R.id.txt_nombreTiendaEdit).setText(arreglo.get(idCategoria).nombreCategoria)
        findViewById<EditText>(R.id.txt_RUCEdit).setText(arreglo.get(idCategoria).fechaCreacion)
        findViewById<EditText>(R.id.txt_telefonoEdit).setText((arreglo.get(idCategoria).prioridadCategoria.toString()))
        findViewById<EditText>(R.id.txt_propietarioEdit).setText(arreglo.get(idCategoria).tipoCategoria)

        val botonActualizarCategoria = findViewById<Button>(R.id.btn_ActualizarTienda)
        botonActualizarCategoria.setOnClickListener {
            var nombreCategoriaAct = findViewById<EditText>(R.id.txt_nombreTiendaEdit).text.toString()
            var descripcionCategoriaAct = findViewById<EditText>(R.id.txt_direccionEdit).text.toString()
            var fechaCreacionCategoriaAct = findViewById<EditText>(R.id.txt_RUCEdit).text.toString()
            var prioridadCategoriaAct = Integer.parseInt(findViewById<EditText>(R.id.txt_telefonoEdit).text.toString())
            var tipoCategoriaAct = findViewById<EditText>(R.id.txt_propietarioEdit).text.toString()
            arreglo.get(idCategoria).nombreCategoria = nombreCategoriaAct
            arreglo.get(idCategoria).fechaCreacion = fechaCreacionCategoriaAct
            arreglo.get(idCategoria).prioridadCategoria = prioridadCategoriaAct
            arreglo.get(idCategoria).tipoCategoria = tipoCategoriaAct

            finish()

        }
    }
}