package com.example.laboratorio7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class NewAccountFragment : Fragment(R.layout.fragment_newaccount) {
    private lateinit var inputField: EditText
    private lateinit var btnRegister: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister = view.findViewById(R.id.btn_register)
        inputField = view.findViewById(R.id.inputfieldRegister)

        setListeners()
    }

    private fun setListeners(){
        btnRegister.setOnClickListener {
            val tEmail = inputField.text.toString()
            val action = NewAccountFragmentDirections.actionNewAccountFragmentToLoginFragment(email = tEmail)
            requireView().findNavController().navigate(action)
        }
    }

}