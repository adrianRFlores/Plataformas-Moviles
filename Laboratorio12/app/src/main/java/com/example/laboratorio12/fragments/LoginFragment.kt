package com.example.laboratorio12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import androidx.navigation.findNavController
import com.example.laboratorio12.ViewModelObj
import com.example.laboratorio12.databinding.FragmentLoginBinding
import com.example.laboratorio12.visibleIf
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private val vModel: ViewModelObj by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonLoginFragmentLogin.setOnClickListener { vModel.login(
            email = binding.inputLayoutLoginFragmentEmail.editText!!.text.toString(),
            password = binding.inputLayoutLoginFragmentPassword.editText!!.text.toString()
        )
        }
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            vModel.loginStatus.collectLatest { state ->
                loginHandler(state)
            }
        }
    }

    private fun loginHandler(state: ViewModelObj.LoginState) {
        when(state) {
            ViewModelObj.LoginState.Default -> {
                binding.progressbarLogin.visibleIf(false)
                binding.buttonLoginFragmentLogin.visibleIf(true)
            }
            ViewModelObj.LoginState.Success -> {
                vModel.generateToken()
                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }
            is ViewModelObj.LoginState.Error -> {
                Snackbar.make(binding.root, state.message, Snackbar.LENGTH_SHORT).show()
                binding.buttonLoginFragmentLogin.visibleIf(true)
                binding.progressbarLogin.visibleIf(false)

            }
            ViewModelObj.LoginState.Loading -> {
                binding.buttonLoginFragmentLogin.visibility = View.INVISIBLE
                binding.progressbarLogin.visibleIf(true)

            }
        }
    }
}