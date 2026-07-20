package com.example.firepassword.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firepassword.data.repository.AuthRepository
import com.example.firepassword.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<Resource<Unit>>(Resource.Idle)

    val state = _state.asStateFlow()

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            _state.value = Resource.Loading

            _state.value =
                repository.login(
                    email,
                    password
                )
        }

    }

    fun resetState() {
        _state.value = Resource.Idle
    }

    class Factory(
        private val repository: AuthRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {

            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(repository) as T
            }

            throw IllegalArgumentException()

        }
    }
}