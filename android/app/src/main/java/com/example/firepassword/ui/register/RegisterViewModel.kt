package com.example.firepassword.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firepassword.data.repository.AuthRepository
import com.example.firepassword.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<Resource<Unit>>(Resource.Idle)

    val state = _state.asStateFlow()

    fun register(
        username: String,
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            _state.value = Resource.Loading

            _state.value =
                repository.register(
                    username,
                    email,
                    password
                )
        }
    }



    fun resetState() {
        _state.value = Resource.Idle
    }

    class Factory(private val repository: AuthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RegisterViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}