package com.example.laboratorio4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var toast: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = findViewById(R.id.btn_mainActivity_Iniciar)
        initListener()
    }
    private fun initListener() {
        toast.setOnClickListener {
            Toast.makeText(applicationContext, "Adrián Ricardo Flores Trujillo", Toast.LENGTH_LONG).show()
        }
    }
}