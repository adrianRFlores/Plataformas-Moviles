package com.example.laboratorio4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var toast: Button
    private lateinit var btnDireccion: ImageButton
    private lateinit var btnDetalles: Button
    private lateinit var txtNombre: TextView
    private lateinit var txtDir: TextView
    private lateinit var txtHora: TextView
    private lateinit var txtDescargar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = findViewById(R.id.btn_mainActivity_Iniciar)
        btnDireccion = findViewById(R.id.btn_mainActivity_Direccion)
        btnDetalles = findViewById(R.id.btn_mainActivity_detalles)
        txtNombre = findViewById(R.id.txt_mainActivity_restaurante)
        txtDir = findViewById(R.id.txt_mainActivity_direccion)
        txtHora = findViewById(R.id.txt_mainActivity_horario)
        txtDescargar = findViewById(R.id.txt_mainActivity_descargar)
        initListeners()
    }

    private fun initListeners() {
        toast.setOnClickListener {
            Toast.makeText(applicationContext, "Adrián Ricardo Flores Trujillo", Toast.LENGTH_LONG).show()
        }

        btnDireccion.setOnClickListener {
            val location = "https://goo.gl/maps/87evtv1Hf9ma8HQKA"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }

        btnDetalles.setOnClickListener {

            val nombre = txtNombre.text.toString()
            val direccion = txtDir.text.toString()
            val horario = txtHora.text.toString()
            val data = Data(nombre, direccion, horario)

            val intent = Intent(this, DetailsActivity::class.java)

            intent.putExtra("EXTRA_DATA", data)
            startActivity(intent)
        }

        txtDescargar.setOnClickListener {
            val url = "https://play.google.com/store/apps/details?id=com.instagram.android"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}