package com.example.listadetareas

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MisNotas : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adaptador: adaptadorNotas
    private val listaNotas = mutableListOf<nota>()

    lateinit var bagregar:Button
    lateinit var Brea:Button
    lateinit var claveRecibida:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_notas)
        listView = findViewById(R.id.listView)
//        recibo la clave par bajar las listas de un usuario
        val intent=intent
        if (intent.hasExtra("clave")){
            claveRecibida = intent.getStringExtra("clave").toString()

        }
        instanciarbotones()
        adaptador = adaptadorNotas(this, listaNotas)
        listView.adapter = adaptador
        clicklista()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mi:MenuInflater=menuInflater
        mi.inflate(R.menu.manu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val i=Intent(this,tareasRealizadas::class.java)
                startActivity(i)
                return true
            }
        }
        return true
        //no me muestra
        //
        //

    }

    private fun clicklista() {
        listView.setOnItemClickListener { parent, view, position, id ->
            val int=Intent(this,editarNota::class.java)
            int.putExtra("clave",claveRecibida)
            int.putExtra("pos",position.toString())
            int.putExtra("ac","minota")
            startActivity(int)
        }
    }

//         Manejar clics en elementos de la lista (ver descripción de la nota)


        private fun instanciarbotones() {
            bagregar=findViewById(R.id.BMagregar)
            bagregar.setOnClickListener {
                var i= Intent(this,AgregarTarea::class.java)
                i.putExtra("clave",claveRecibida)
                startActivity(i)
            }

            Brea=findViewById(R.id.realizada)
            Brea.setOnClickListener {
                var i= Intent(this,tareasRealizadas::class.java)
                i.putExtra("clave",claveRecibida)
                startActivity(i)
            }

        }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = this.getSharedPreferences("Notas", Context.MODE_PRIVATE)
            if (sharedPreferences.contains(claveRecibida.toString())) {
                val listaUN=sharedPreferences.getString(claveRecibida,null)
                val gson= Gson()
                val type=object : TypeToken<MutableList<Array<String>>>(){}.type
                val listaRecu:MutableList<Array<String>> = gson.fromJson(listaUN,type)
                mostrarnotas(listaRecu)
            }
            else{
                Toast.makeText(this, "No hay tareas", Toast.LENGTH_SHORT).show()
            }

        }

    private fun mostrarnotas(listaRecuperada:MutableList<Array<String>>) {
        var n:Long=0
//      limpia la lista por si ya tienen elementos
        listaNotas.clear()
        for (array in listaRecuperada) {
            n++
            val t=array[0].toString()
            val d=array[1].toString()
            val nuevaNota1 = nota(n,t, d)
            listaNotas.add(nuevaNota1)
        }
        adaptador = adaptadorNotas(this, listaNotas)
        listView.adapter = adaptador

    }

}
