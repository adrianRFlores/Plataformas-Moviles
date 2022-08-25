package com.example.laboratorio7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var helloText: TextView
    private lateinit var btnActualizar: Button
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tEmail = args.email
        helloText = view.findViewById(R.id.prompt)
        btnActualizar = view.findViewById(R.id.actualizarBtn)
        helloText.text = "Hola $tEmail, ¡necesitamos que\n actualices tu perfil!"

        setListeners(tEmail)
    }

    private fun setListeners(str: String) {
        btnActualizar.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(email = str)
            requireView().findNavController().navigate(action)
        }
    }

}