package com.hv.community.android.presentation.ui.login.detail

import androidx.lifecycle.viewModelScope
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.UserHasEmailTokenUseCase
import com.hv.community.android.domain.usecase.user.UserSignInUseCase
import com.hv.community.android.presentation.common.REGEX_EMAIL
import com.hv.community.android.presentation.common.base.BaseViewModel
import com.hv.community.android.presentation.common.util.coroutine.event.EventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.MutableEventFlow
import com.hv.community.android.presentation.common.util.coroutine.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginDetailViewModel @Inject constructor(
    private val userSignInUseCase: UserSignInUseCase,
    private val userHasEmailTokenUseCase: UserHasEmailTokenUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<LoginDetailState> = MutableStateFlow(LoginDetailState.Init)
    val state: StateFlow<LoginDetailState> = _state.asStateFlow()

    private val _event: MutableEventFlow<LoginDetailViewEvent> = MutableEventFlow()
    val event: EventFlow<LoginDetailViewEvent> = _event.asEventFlow()

    val isLoading: StateFlow<Boolean> = state.map { state ->
        return@map when (state) {
            is LoginDetailState.Init -> false
            is LoginDetailState.Loading -> true
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val email: MutableStateFlow<String> = MutableStateFlow("")

    private val _isEmailValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid.asStateFlow()

    val password: MutableStateFlow<String> = MutableStateFlow("")

    private val _isPasswordValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid.asStateFlow()

    init {
        launch {
            email.collect { email ->
                if (!isEmailValid.value && isEmailValid(email)) {
                    _isEmailValid.value = true
                }
            }
        }
        launch {
            password.collect { password ->
                if (!isPasswordValid.value && isPasswordValid(password)) {
                    _isPasswordValid.value = true
                }
            }
        }
    }

    fun onConfirm() {
        launch {
            val isEmailValid = isEmailValid(email.value)
            val isPasswordValid = isPasswordValid(password.value)

            _isEmailValid.value = isEmailValid
            _isPasswordValid.value = isPasswordValid

            when {
                !isEmailValid -> {
                    _event.emit(LoginDetailViewEvent.Validation.InvalidEmail)
                }

                !isPasswordValid -> {
                    _event.emit(LoginDetailViewEvent.Validation.InvalidPassword)
                }

                else -> {
                    signIn()
                }
            }
        }
    }

    fun onRegistration() {
        launch {
            val hasEmailToken = userHasEmailTokenUseCase()

            if (hasEmailToken) {
                _event.emit(LoginDetailViewEvent.GoRegistrationConfirm)
            } else {
                _event.emit(LoginDetailViewEvent.GoRegistration)
            }
        }
    }

    fun onBack() {
        launch {
            _event.emit(LoginDetailViewEvent.GoBack)
        }
    }

    private suspend fun signIn() {
        _state.value = LoginDetailState.Loading

        userSignInUseCase(
            email = email.value,
            password = password.value
        ).onSuccess {
            _event.emit(LoginDetailViewEvent.Login.Success)
        }.onFailure {
            when (it) {
                is ServerException -> {
                    _event.emit(LoginDetailViewEvent.Login.Fail)
                }

                else -> {
                    _event.emit(LoginDetailViewEvent.Login.Error)
                }
            }
        }

        _state.value = LoginDetailState.Init
    }

    private fun isEmailValid(email: String): Boolean {
        return email.matches(REGEX_EMAIL.toRegex())
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
