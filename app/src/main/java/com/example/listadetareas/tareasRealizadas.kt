package com.example.listadetareas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class tareasRealizadas : AppCompatActivity() {
    lateinit var nuevo: Array<String>
    lateinit var claveRecibida:String

    lateinit var TR:ListView
    private lateinit var adaptador2: adaptadorNotas
    private val listaNotas2 = mutableListOf<nota>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas_realizadas)
        TR=findViewById(R.id.listReaa)
        adaptador2 = adaptadorNotas(this, listaNotas2)
        TR.adapter = adaptador2
        val inten=intent
        claveRecibida= inten.getStringExtra("clave").toString()
        if (inten.hasExtra("realizada")){
            nuevo= inten.getStringArrayExtra("realizada") as Array<String>
            guardarLista()
        }
        clicklista()
    }

    private fun clicklista() {
        TR.setOnItemClickListener { parent, view, position, id ->
            val int= Intent(this,editarNota::class.java)
            int.putExtra("clave",claveRecibida)
            int.putExtra("pos",position.toString())
            int.putExtra("ac","r")
            startActivity(int)
        }
    }
    private fun guardarLista() {
        val sharedPreferences = this.getSharedPreferences("NotasR", Context.MODE_PRIVATE)
        if (sharedPreferences.contains(claveRecibida)) {
            //existe la clave
            val listaUN=sharedPreferences.getString(claveRecibida,null)
            val gson= Gson()
            val type=object : TypeToken<MutableList<Array<String>>>(){}.type
            val listaRecu:MutableList<Array<String>> = gson.fromJson(listaUN,type)
            guardarNotaR(listaRecu)

        }
        else{
            //no existe la clave
            val listan:MutableList<Array<String>> = mutableListOf()
            guardarNotaR(listan)
        }
    }

    private fun guardarNotaR(listaRecu: MutableList<Array<String>>) {
        val nuevan= arrayOf(nuevo[0].toString(),nuevo[1])
        listaRecu.add(nuevan)
        gpersistente2(listaRecu)
    }
    private fun gpersistente2(listaRecu: MutableList<Array<String>>) {
        val gson=Gson()
        val listaNotasJson= gson.toJson(listaRecu)
        val sharedPreferences: SharedPreferences =this.getSharedPreferences("NotasR",Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(claveRecibida,listaNotasJson)
        edit.apply()
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = this.getSharedPreferences("NotasR", Context.MODE_PRIVATE)
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
        listaNotas2.clear()
        for (array in listaRecuperada) {
            n++
            val t=array[0].toString()
            val d=array[1].toString()
            val nuevaNota1 = nota(n,t, d)
            listaNotas2.add(nuevaNota1)
        }
        adaptador2 = adaptadorNotas(this, listaNotas2)
        TR.adapter = adaptador2

    }
}