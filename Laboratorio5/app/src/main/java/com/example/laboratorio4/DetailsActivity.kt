package com.example.laboratorio4

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class DetailsActivity : AppCompatActivity() {

    private lateinit var btnVisita: Button
    private lateinit var constLayout: ConstraintLayout
    private lateinit var btnLlamar: TextView
    private lateinit var restText: TextView
    private lateinit var dirText: TextView
    private lateinit var horaText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val data: Data = intent.getSerializableExtra("EXTRA_DATA") as Data

        btnVisita = findViewById(R.id.btn_DetailsActivity_initVisita)
        constLayout = findViewById(R.id.mainLayout)
        btnLlamar = findViewById(R.id.txtbtn_DetailsActivity_llamar)
        restText = findViewById(R.id.txt_restaurante)
        dirText = findViewById(R.id.txt_direccion)
        horaText = findViewById(R.id.txt_horario)

        restText.text = data.nombre
        dirText.text = data.direccion
        horaText.text = data.horario

        initListeners()
    }

    private fun initListeners() {
        btnVisita.setOnClickListener {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 0)

        }

        btnLlamar.setOnClickListener {
            val phoneNum = "+5022508809"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }
    }

}