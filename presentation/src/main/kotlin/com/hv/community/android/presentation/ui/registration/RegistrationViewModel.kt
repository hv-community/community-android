package com.hv.community.android.presentation.ui.registration

import androidx.lifecycle.viewModelScope
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.UserSignUpUseCase
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
class RegistrationViewModel @Inject constructor(
    private val userSignUpUseCase: UserSignUpUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Init)
    val state: StateFlow<RegistrationState> = _state.asStateFlow()

    private val _event: MutableEventFlow<RegistrationViewEvent> = MutableEventFlow()
    val event: EventFlow<RegistrationViewEvent> = _event.asEventFlow()

    val isLoading: StateFlow<Boolean> = state.map { state ->
        return@map when (state) {
            is RegistrationState.Init -> false
            is RegistrationState.Loading -> true
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val nickname: MutableStateFlow<String> = MutableStateFlow("")

    private val _isNicknameValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isNicknameValid: StateFlow<Boolean> = _isNicknameValid.asStateFlow()

    val email: MutableStateFlow<String> = MutableStateFlow("")

    private val _isEmailValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid.asStateFlow()

    val password: MutableStateFlow<String> = MutableStateFlow("")

    private val _isPasswordValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isPasswordValid: StateFlow<Boolean> = _isPasswordValid.asStateFlow()

    val passwordConfirm: MutableStateFlow<String> = MutableStateFlow("")

    private val _isPasswordConfirmValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isPasswordConfirmValid: StateFlow<Boolean> = _isPasswordConfirmValid.asStateFlow()

    init {
        launch {
            nickname.collect { nickname ->
                if (!isNicknameValid.value && isNicknameValid(nickname)) {
                    _isNicknameValid.value = true
                }
            }
        }
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
        launch {
            passwordConfirm.collect { passwordConfirm ->
                if (!isPasswordConfirmValid.value && isPasswordConfirmValid(password.value, passwordConfirm)) {
                    _isPasswordConfirmValid.value = true
                }
            }
        }
    }

    fun onConfirm() {
        launch {
            val isNicknameValid = isNicknameValid(nickname.value)
            val isEmailValid = isEmailValid(email.value)
            val isPasswordValid = isPasswordValid(password.value)
            val isPasswordConfirmValid = isPasswordConfirmValid(password.value, passwordConfirm.value)

            _isNicknameValid.value = isNicknameValid
            _isEmailValid.value = isEmailValid
            _isPasswordValid.value = isPasswordValid
            _isPasswordConfirmValid.value = isPasswordConfirmValid

            when {
                !isNicknameValid -> {
                    _event.emit(RegistrationViewEvent.Validation.InvalidNickname)
                }

                !isEmailValid -> {
                    _event.emit(RegistrationViewEvent.Validation.InvalidEmail)
                }

                !isPasswordValid -> {
                    _event.emit(RegistrationViewEvent.Validation.InvalidPassword)
                }

                !isPasswordConfirmValid -> {
                    _event.emit(RegistrationViewEvent.Validation.InvalidPasswordConfirm)
                }

                else -> {
                    signUp()
                }
            }
        }
    }

    fun onBack() {
        launch {
            _event.emit(RegistrationViewEvent.GoBack)
        }
    }

    private suspend fun signUp() {
        _state.value = RegistrationState.Loading

        userSignUpUseCase(
            email = email.value,
            nickname = nickname.value,
            password = password.value
        ).onSuccess {
            _event.emit(RegistrationViewEvent.Registration.Success)
        }.onFailure {
            when (it) {
                is ServerException -> {
                    _event.emit(RegistrationViewEvent.Registration.Fail)
                }

                else -> {
                    _event.emit(RegistrationViewEvent.Registration.Error)
                }
            }
        }

        _state.value = RegistrationState.Init
    }

    private fun isNicknameValid(nickname: String): Boolean {
        return nickname.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.matches(REGEX_EMAIL.toRegex())
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    private fun isPasswordConfirmValid(password: String, passwordConfirm: String): Boolean {
        return password == passwordConfirm
    }
}
