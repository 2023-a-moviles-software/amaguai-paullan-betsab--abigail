package com.example.examen_moviles

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarCategoria : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_categoria)
        val objetoIntent : Intent = intent
        var idCategoria = objetoIntent.getStringExtra("idCategoria")
        consultarCategoriaPorId(idCategoria!!)

        val botonActualizarCategoria = findViewById<Button>(R.id.btn_ActualizarTienda)
        botonActualizarCategoria.setOnClickListener {
            var nombreCategoriaAct = findViewById<EditText>(R.id.txt_nombreCategoriaEdit).text.toString()
            var fechaCreacionCategoriaAct = findViewById<EditText>(R.id.txt_fechaCategoriaEdit).text.toString()
            var prioridadCategoriaAct = Integer.parseInt(findViewById<EditText>(R.id.txt_prioridadCategoriaEdit).text.toString())
            var tipoCategoriaAct = findViewById<EditText>(R.id.txt_tipoCategoriaEdit).text.toString()

            actualizarCategoria(idCategoria!!, nombreCategoriaAct, fechaCreacionCategoriaAct, prioridadCategoriaAct, tipoCategoriaAct)

            finish()

        }
    }

    fun consultarCategoriaPorId(idCategoria : String){
        val db = Firebase.firestore
        val categoriasReferencia = db.collection("categorias")
        categoriasReferencia
            .document(idCategoria)
            .get()
            .addOnSuccessListener {
                findViewById<TextView>(R.id.tv_tituloNombreTienda).text = it.data?.get("nombre") as String?
                findViewById<EditText>(R.id.txt_nombreCategoriaEdit).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.txt_fechaCategoriaEdit).setText(it.data?.get("fecha") as String?)
                findViewById<EditText>(R.id.txt_tipoCategoriaEdit).setText(it.data?.get("tipoCategoria") as String?)
                findViewById<EditText>(R.id.txt_prioridadCategoriaEdit).setText((it.data?.get("prioridad") as Number?).toString())
            }
    }

    fun actualizarCategoria(idCategoria: String ,nombre: String, fecha: String, prioridad: Number, tipoCategoria: String ){
        var db = Firebase.firestore
        var tiendasReferencia = db.collection("categorias").document(idCategoria)
        tiendasReferencia.set(
            hashMapOf(
                "nombre" to nombre,
                "fecha" to fecha,
                "prioridad" to prioridad,
                "tipoCategoria" to tipoCategoria,
            )
        )

    }

}