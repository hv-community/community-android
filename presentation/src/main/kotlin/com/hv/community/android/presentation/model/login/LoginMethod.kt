package com.hv.community.android.presentation.model.login

import com.hv.community.android.presentation.R

sealed class LoginMethod(
    val title: String,
    val iconRes: Int
) {
    data object Anonymous : LoginMethod(
        title = "비회원으로 시작하기",
        iconRes = R.drawable.ic_anonymous
    )

    data object Kakao : LoginMethod(
        title = "카카오로 시작하기",
        iconRes = R.drawable.ic_kakao
    )

    data object Google : LoginMethod(
        title = "구글로 시작하기",
        iconRes = R.drawable.ic_google
    )

    data object Email : LoginMethod(
        title = "이메일로 시작하기",
        iconRes = R.drawable.ic_user
    )
}
