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

class ListaNotas : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloCategorias
    var adaptador : ArrayAdapter<Notas>? = null
    var idCategoria : Int = 10
    var arregloNotas : ArrayList<Notas>? = null
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        val objetoIntent : Intent = intent
        idCategoria = objetoIntent.getIntExtra("idCategoriaListaNotas",3)
        val listView = findViewById<ListView>(R.id.lv_notas)
        arregloNotas = arreglo.get(idCategoria).notas
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arregloNotas!!
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        findViewById<TextView>(R.id.tv_nombreCategoriaNotas).setText(arreglo.get(idCategoria).nombreCategoria)
        var botonCrearNota = findViewById<Button>(R.id.btn_crearNota)
        botonCrearNota.setOnClickListener {
            intent = Intent(this, CrearNota::class.java )
            intent.putExtra("idCategoriaNota", idCategoria)
            startActivity(intent)
            adaptador!!.notifyDataSetChanged()
        }

    }

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
        return when (item.itemId) {
            R.id.mi_editarNota -> {
                intent = Intent(this,EditarNota::class.java)
                intent.putExtra("idCategoria",idCategoria)
                intent.putExtra("idNota",idItemSeleccionado)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminarNota -> {
                abrirDialogo()
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarNota()
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }


    fun eliminarNota(){
        arregloNotas?.removeAt(idItemSeleccionado)
    }



    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
    }


}