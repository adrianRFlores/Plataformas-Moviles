package com.example.laboratorio7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var inputField: EditText
    private lateinit var loginBtn: Button
    private lateinit var txtRegister: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn = view.findViewById(R.id.buttonLogin)
        inputField = view.findViewById(R.id.input)
        txtRegister = view.findViewById(R.id.txt_register)

        try{ // este try está aca porque me da miedo que explote el app si se inicia por primera vez, ya que no tendria args y no sé si petaria el setText
            val args: LoginFragmentArgs by navArgs()
            inputField.setText(args.email)
        } catch(e: Exception){}

        setListeners()
    }

    private fun setListeners(){
        loginBtn.setOnClickListener{
            val tEmail = inputField.text.toString()
            if(tEmail == "jcdurini@uvg.edu.gt"){
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(email = tEmail)
                requireView().findNavController().navigate(action)
            } else Toast.makeText(activity, "El correo ingresado no existe", Toast.LENGTH_SHORT).show()
        }
        txtRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            requireView().findNavController().navigate(action)
        }
    }


}