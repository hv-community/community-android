package com.hv.community.android.data.remote.local

import android.content.Context
import com.hv.community.android.data.R

class ErrorMessageMapper(
    private val context: Context
) {
    fun map(
        key: String
    ): String {
        val id = when (key) {
            "ACCESS:FORBIDDEN_ACCESS" -> R.string.error_access_forbidden_access
            "MAIL:SEND_MAIL_FAIL" -> R.string.error_mail_send_mail_fail
            "MEMBER:EMAIL_FORM_ERROR" -> R.string.error_member_email_form_error
            "MEMBER:NICKNAME_FORM_ERROR" -> R.string.error_member_nickname_form_error
            "MEMBER:PASSWORD_FORM_ERROR" -> R.string.error_member_password_form_error
            "MEMBER:MAIL_EXIST" -> R.string.error_member_mail_exist
            "MEMBER:NICKNAME_EXIST" -> R.string.error_member_nickname_exist
            "MEMBER:MEMBER_UNREGISTER" -> R.string.error_member_member_unregister
            "MEMBER:REFRESH_TOKEN_INVALID" -> R.string.error_member_refresh_token_invalid
            "TOKEN:TOKEN_EXPIRED" -> R.string.error_token_token_expired
            "TOKEN:TOKEN_INVALID" -> R.string.error_token_token_invalid
            "TOKEN:UNAUTHORIZED" -> R.string.error_token_unauthorized
            "COMMUNITY:PAGE_INVALID" -> R.string.error_community_page_invalid
            "COMMUNITY:COMMUNITY_INVALID" -> R.string.error_community_community_invalid
            "COMMUNITY:POST_INVALID" -> R.string.error_community_post_invalid
            "COMMUNITY:REPLY_INVALID" -> R.string.error_community_reply_invalid
            "COMMUNITY:EMPTY_TITLE_OR_CONTENT" -> R.string.error_community_empty_title_or_content
            "COMMUNITY:PASSWORD_INVALID" -> R.string.error_community_password_invalid
            "COMMUNITY:PERMISSION_INVALID" -> R.string.error_community_permission_invalid
            else -> R.string.error_unknown
        }

        return context.getString(id)
    }
}