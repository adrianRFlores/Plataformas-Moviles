package com.example.laboratorio12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laboratorio12.R
import com.example.laboratorio12.ViewModelObj
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.collectLatest
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import android.widget.Toast
import com.example.laboratorio12.databinding.FragmentHomeBinding
import com.example.laboratorio12.visibleIf

class HomeFragment : Fragment() {

    private val home: HomeModel by viewModels()
    private val session: ViewModelObj by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        setListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setListeners() {
        binding.apply {

            buttonCerrarSesion.setOnClickListener {
                session.logout()
            }

            buttonMantenerSesion.setOnClickListener {
                session.cancel()
            }

            buttonDefault.setOnClickListener {
                home.updateViewState(HomeModel.HomeViewState.Default)
            }

            buttonSuccess.setOnClickListener {
                home.updateViewState(HomeModel.HomeViewState.Success)
            }

            buttonFailure.setOnClickListener {
                home.updateViewState(HomeModel.HomeViewState.Failure)
            }

            buttonEmpty.setOnClickListener {
                home.updateViewState(HomeModel.HomeViewState.Empty)
            }

        }
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            session.validAuthToken.collectLatest { isValid ->
                if (!isValid) {
                    Toast.makeText(requireContext(), "Sesión Cerrada", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            home.homeState.collectLatest { state ->
                homeHandler(state)
            }
        }

    }

    private fun homeHandler(state: HomeModel.HomeViewState) {
        when(state) {
            HomeModel.HomeViewState.Default -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_android_24)
                )
                binding.textHomeFragmentDescription.text = "Selecciona una opción"
            }

            HomeModel.HomeViewState.Success -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_circle_outline_24)
                )
                binding.textHomeFragmentDescription.text = "Success"
            }

            HomeModel.HomeViewState.Failure -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_outline_24)
                )
                binding.textHomeFragmentDescription.text = "Failure"
            }

            HomeModel.HomeViewState.Empty -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_do_not_disturb_24)
                )
                binding.textHomeFragmentDescription.text = "Empty"
            }

            is HomeModel.HomeViewState.Loading -> {
                setStateLoading(true)
                binding.buttonDefault.isEnabled = state.currentState == HomeModel.HomeViewState.Default
                binding.buttonSuccess.isEnabled = state.currentState == HomeModel.HomeViewState.Success
                binding.buttonFailure.isEnabled = state.currentState == HomeModel.HomeViewState.Failure
                binding.buttonEmpty.isEnabled = state.currentState == HomeModel.HomeViewState.Empty
            }
        }
    }

    private fun setStateLoading(loading: Boolean) {
        binding.iconHomeFragmentStatus.visibleIf(!loading)
        binding.textHomeFragmentDescription.visibleIf(!loading)
        binding.progressbar.visibleIf(loading)
    }

    private fun enableButtons() {
        binding.buttonDefault.isEnabled = true
        binding.buttonEmpty.isEnabled = true
        binding.buttonFailure.isEnabled = true
        binding.buttonSuccess.isEnabled = true
    }

}