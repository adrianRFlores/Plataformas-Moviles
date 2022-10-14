package com.example.laboratorio12.fragments

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay

class HomeModel : ViewModel() {

    sealed class HomeViewState {
        object Default: HomeViewState()
        object Success: HomeViewState()
        object Failure: HomeViewState()
        object Empty: HomeViewState()
        class Loading(val currentState: HomeViewState = HomeViewState.Default): HomeViewState()
    }

    private val _homeState = MutableStateFlow<HomeViewState>(HomeViewState.Default)
    val homeState: StateFlow<HomeViewState> = _homeState

    fun updateViewState(state: HomeViewState) {
        viewModelScope.launch {
            _homeState.value = HomeViewState.Loading(currentState = state)
            delay(2000L)
            _homeState.value = state
        }
    }
}