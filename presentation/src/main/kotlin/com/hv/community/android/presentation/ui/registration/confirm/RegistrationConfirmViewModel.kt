package com.hv.community.android.presentation.ui.registration.confirm

import androidx.lifecycle.viewModelScope
import com.hv.community.android.domain.model.error.ServerException
import com.hv.community.android.domain.usecase.user.UserGetLoginDataUseCase
import com.hv.community.android.domain.usecase.user.UserSendEmailUseCase
import com.hv.community.android.domain.usecase.user.UserVerifyEmailUseCase
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
class RegistrationConfirmViewModel @Inject constructor(
    private val userGetLoginDataUseCase: UserGetLoginDataUseCase,
    private val userSendEmailUseCase: UserSendEmailUseCase,
    private val userVerifyEmailUseCase: UserVerifyEmailUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<RegistrationConfirmState> = MutableStateFlow(RegistrationConfirmState.Init)
    val state: StateFlow<RegistrationConfirmState> = _state.asStateFlow()

    private val _event: MutableEventFlow<RegistrationConfirmViewEvent> = MutableEventFlow()
    val event: EventFlow<RegistrationConfirmViewEvent> = _event.asEventFlow()

    val isLoading: StateFlow<Boolean> = state.map { state ->
        return@map when (state) {
            is RegistrationConfirmState.Init -> false
            is RegistrationConfirmState.Loading -> true
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    private val _email: MutableStateFlow<String> = MutableStateFlow(userGetLoginDataUseCase().first)
    val email: StateFlow<String> = _email.asStateFlow()

    val verificationCode: MutableStateFlow<String> = MutableStateFlow("")

    private val _isVerificationCodeValid: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isVerificationCodeValid: StateFlow<Boolean> = _isVerificationCodeValid.asStateFlow()

    private var lastEmailResendTime: Long = 0

    init {
        launch {
            verificationCode.collect { verificationCode ->
                if (!isVerificationCodeValid.value && isVerificationCodeValid(verificationCode)) {
                    _isVerificationCodeValid.value = true
                }
            }
        }
    }

    fun onConfirm() {
        launch {
            val isVerificationCodeValid = isVerificationCodeValid(verificationCode.value)

            _isVerificationCodeValid.value = isVerificationCodeValid

            when {
                !isVerificationCodeValid -> {
                    _event.emit(RegistrationConfirmViewEvent.Validation.InvalidVerificationCode)
                }

                else -> {
                    verifyEmail(verificationCode.value)
                }
            }
        }
    }

    fun onBack() {
        launch {
            _event.emit(RegistrationConfirmViewEvent.GoBack)
        }
    }

    fun onResend() {
        launch {
            val currentTimeMillis = System.currentTimeMillis()
            if (currentTimeMillis - lastEmailResendTime > RegistrationConfirmConstant.INTERVAL_EMAIL_RESEND) {
                lastEmailResendTime = currentTimeMillis
                resendEmail()
            } else {
                _event.emit(RegistrationConfirmViewEvent.Resend.Prevent)
            }
        }
    }

    private suspend fun verifyEmail(
        verificationCode: String
    ) {
        _state.value = RegistrationConfirmState.Loading

        userVerifyEmailUseCase(
            verificationCode = verificationCode
        ).onSuccess {
            _event.emit(RegistrationConfirmViewEvent.Registration.Success)
        }.onFailure {
            when (it) {
                is ServerException -> {
                    _event.emit(RegistrationConfirmViewEvent.Registration.Fail)
                }

                else -> {
                    _event.emit(RegistrationConfirmViewEvent.Registration.Error)
                }
            }
        }

        _state.value = RegistrationConfirmState.Init
    }

    private suspend fun resendEmail() {
        _state.value = RegistrationConfirmState.Loading

        userSendEmailUseCase().onSuccess {
            verificationCode.value = ""
            _event.emit(RegistrationConfirmViewEvent.Resend.Success)
        }.onFailure {
            when (it) {
                is ServerException -> {
                    _event.emit(RegistrationConfirmViewEvent.Resend.Fail)
                }

                else -> {
                    _event.emit(RegistrationConfirmViewEvent.Resend.Error)
                }
            }
        }

        _state.value = RegistrationConfirmState.Init
    }

    private fun isVerificationCodeValid(verificationCode: String): Boolean {
        return verificationCode.length == 6
    }
}
