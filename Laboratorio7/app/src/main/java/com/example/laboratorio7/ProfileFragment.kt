package com.example.laboratorio7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var inputField: EditText
    private lateinit var btnActualizar: Button
    private val args: ProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = args.email
        inputField = view.findViewById(R.id.inputfieldProfile)
        btnActualizar = view.findViewById(R.id.btnActualizarProfile)
        inputField.setText(email)
        setListeners()
    }

    private fun setListeners() {
        btnActualizar.setOnClickListener {
            val temp = inputField.text.toString()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment(email = temp)
            requireView().findNavController().navigate(action)
        }
    }

}