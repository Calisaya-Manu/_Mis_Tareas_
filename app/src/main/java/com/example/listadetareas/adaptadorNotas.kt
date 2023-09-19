package com.example.listadetareas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class adaptadorNotas(private val context: Context, private val listaNotas: List<nota>) : BaseAdapter() {
    private val colores = intArrayOf(R.color.colorNota1, R.color.colorNota2) // Dos colores disponibles
    private var colorIndex = 0
    override fun getCount(): Int {
        return listaNotas.size
    }

    override fun getItem(position: Int): Any {
        return listaNotas[position]
    }

    override fun getItemId(position: Int): Long {
        return listaNotas[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)
        val nota = getItem(position) as nota

        val nombreTextView = view.findViewById<TextView>(R.id.textViewNombre)
        nombreTextView.text = nota.nombre

        val colorResource = colores[colorIndex]
        nombreTextView.setTextColor(ContextCompat.getColor(context, colorResource))

        // Alterna el índice de color para la próxima nota
        colorIndex = (colorIndex + 1) % colores.size

        // Manejar clics en los botones de editar y eliminar aquí

        return view
    }
}