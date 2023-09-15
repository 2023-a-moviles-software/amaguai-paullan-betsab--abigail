package com.example.examen_moviles

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaNotas : AppCompatActivity() {

    var arreglo : ArrayList<Notas> = arrayListOf()
    var adaptador : ArrayAdapter<Notas>? = null
    var idCategoria : String? = ""
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        val objetoIntent : Intent = intent
        idCategoria = objetoIntent.getStringExtra("idCategoriaNotas")
        var nombreCategoria =objetoIntent.getStringExtra("nombreCategoria")
        val listView = findViewById<ListView>(R.id.lv_notas)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arreglo!!
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        findViewById<TextView>(R.id.tv_nombreCategoriaNotas).setText(nombreCategoria)
        var botonCrearNota = findViewById<Button>(R.id.btn_crearNota)
        botonCrearNota.setOnClickListener {
            intent = Intent(this, CrearNota::class.java )
            intent.putExtra("idCategoriaNota", idCategoria)
            startActivity(intent)
            adaptador!!.notifyDataSetChanged()
        }

    }


    fun consultarNota(
        adaptador: ArrayAdapter<Notas>,
        idCategoria : String
    ) {
        val db = Firebase.firestore
        val notasRefUnico = db.collection("categorias").document(idCategoria).collection("notas")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        notasRefUnico
            .get()
            .addOnSuccessListener { // it -> eso (lo que llegue)
                for (nota in it){
                    nota.id
                    anadirAArregloNota(nota)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //Errores
            }
    }

    fun anadirAArregloNota(
        nota: QueryDocumentSnapshot,
    ){
        //ciudad.id
        val nuevaNota = Notas(
            nota.id,
            nota.data.get("nombreNota") as String?,
            nota.data.get("numeroNota") as Number?,
            nota.data.get("prioridad") as Number?,
            nota.data.get("fecha") as String?
        )
        arreglo.add(nuevaNota)
    }

    fun limpiarArreglo() {arreglo.clear()}

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_notas, menu)
        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idNota = arreglo.get(idItemSeleccionado).idNota
        return when (item.itemId) {
            R.id.mi_editarNota -> {
                intent = Intent(this,EditarNota::class.java)
                intent.putExtra("idCategoria",idCategoria)
                intent.putExtra("idNota",idNota)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminarNota -> {
                abrirDialogo(idCategoria!!, idNota!!)
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun abrirDialogo(
        idCategoria: String,
        idNota: String
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarNota(idCategoria, idNota)
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }


    fun eliminarNota(
        idCategoria: String,
        idNota: String
    ){
        var db = Firebase.firestore
        var notaReferencia = db.collection("categorias").document(idCategoria).collection("notas").document(idNota)
        notaReferencia.delete()
        consultarNota(adaptador!!,idCategoria)
    }



    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
        consultarNota(adaptador!!, idCategoria!!)
    }


}