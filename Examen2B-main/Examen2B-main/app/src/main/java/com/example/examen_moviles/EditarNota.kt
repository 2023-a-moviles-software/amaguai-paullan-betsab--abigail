package com.example.examen_moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarNota : AppCompatActivity() {
//    var arreglo = BaseDatosEnMemoria.arregloCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)
        val objetoIntent : Intent = intent
        val idCategoria= objetoIntent.getStringExtra("idCategoria")
        val idNota = objetoIntent.getStringExtra("idNota")

        consultarNotaPorId(idCategoria!!, idNota!!)


        val botonActualizarNota = findViewById<Button>(R.id.btn_actualizarNota)
        botonActualizarNota.setOnClickListener {
            var nombreNota = findViewById<EditText>(R.id.txt_nombreNotaEdit).text.toString()
            var numNota = (findViewById<EditText>(R.id.txt_numNotaEdit).text.toString()).toInt()
            var prioridadNota = (findViewById<EditText>(R.id.txt_prioridadNotaEdit).text.toString()).toInt()
            var fechaNota = findViewById<EditText>(R.id.txt_fechaNotaEdit).text.toString()

            actualizarNota(idCategoria, idNota, nombreNota, numNota, prioridadNota, fechaNota)

            finish()

        }

    }
    fun consultarNotaPorId(idCategoria: String, idNota: String){
        val db = Firebase.firestore
        val notasReferencia = db.collection("categorias").document(idCategoria).collection("notas").document(idNota)
        notasReferencia
            .get()
            .addOnSuccessListener {
                findViewById<TextView>(R.id.tv_nombreNotaEdit).text = it.data?.get("nombreNota") as String?
                findViewById<EditText>(R.id.txt_nombreNotaEdit).setText(it.data?.get("nombreNota") as String?)
                findViewById<EditText>(R.id.txt_numNotaEdit).setText((it.data?.get("numeroNota") as Number?).toString())
                findViewById<EditText>(R.id.txt_prioridadNotaEdit).setText((it.data?.get("prioridad") as Number?).toString())
                findViewById<EditText>(R.id.txt_fechaNotaEdit).setText(it.data?.get("fecha") as String?)
            }
    }

    fun actualizarNota(
        idCategoria: String,
        idNota: String,
        nombre : String,
        numeroNota : Number,
        prioridad : Number,
        fecha : String,
    ){
        var db = Firebase.firestore
        var notasReferencia = db.collection("categorias").document(idCategoria).collection("notas").document(idNota)
        notasReferencia.set(
            hashMapOf(
                "nombreNota" to nombre,
                "numeroNota" to numeroNota,
                "prioridad" to prioridad,
                "fecha" to fecha,
            )
        )

    }

}