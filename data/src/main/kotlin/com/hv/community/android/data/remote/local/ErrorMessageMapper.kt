package com.hv.community.android.data.remote.local

import android.content.Context
import com.hv.community.android.data.R
import com.hv.community.android.domain.model.error.UndefinedKeyException
import timber.log.Timber

class ErrorMessageMapper(
    private val context: Context
) {
    fun map(
        key: String
    ): String {
        val id = when (key) {
            KEY_INTERNAL_SERVER_ERROR -> R.string.error_internal_server_error

            "ACCESS:FORBIDDEN_ACCESS" -> R.string.error_access_forbidden_access
            "MAIL:SEND_MAIL_FAIL" -> R.string.error_mail_send_mail_fail
            "MEMBER:EMAIL_FORM_ERROR" -> R.string.error_member_email_form_error
            "MEMBER:NICKNAME_FORM_ERROR" -> R.string.error_member_nickname_form_error
            "MEMBER:PASSWORD_FORM_ERROR" -> R.string.error_member_password_form_error
            "MEMBER:MAIL_EXIST" -> R.string.error_member_mail_exist
            "MEMBER:NICKNAME_EXIST" -> R.string.error_member_nickname_exist
            "MEMBER:MEMBER_UNREGISTER" -> R.string.error_member_member_unregister
            "MEMBER:REFRESH_TOKEN_INVALID" -> R.string.error_member_refresh_token_invalid
            "MEMBER:GET_MY_PROFILE_ERROR" -> R.string.error_member_get_my_profile_error
            "MEMBER:EMAIL_ACTIVATE_REQUIRE" -> R.string.error_member_email_activate_require
            "MEMBER:EMAIL_OR_PASSWORD_ERROR" -> R.string.error_member_email_or_password_error
            "MEMBER:ACTIVATE_EMAIL_FAIL" -> R.string.error_member_activate_email_fail
            "MEMBER:GET_EMAIL_VERIFICATION_CODE_FAIL" -> R.string.error_member_get_email_verification_code_fail
            "MEMBER:CREATE_EMAIL_VERIFICATION_CODE_FAIL" -> R.string.error_member_create_email_verification_code_fail
            "MEMBER:SIGNUP_FAIL" -> R.string.error_member_signup_fail
            "MEMBER:EMPTY_ACCESS_TOKEN" -> R.string.error_member_empty_access_token
            "MEMBER:MEMBER_UNREGISTERED" -> R.string.error_member_member_unregistered
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
            "COMMUNITY:UNAVAILABLE_USER_NAME" -> R.string.error_community_unavailable_user_name
            "COMMUNITY:REPLY_UPDATE_FAIL" -> R.string.error_community_reply_update_fail
            "COMMUNITY:REPLY_DELETE_FAIL" -> R.string.error_community_reply_delete_fail
            "COMMUNITY:REPLY_CREATE_FAIL" -> R.string.error_community_reply_create_fail
            "COMMUNITY:POST_DELETE_FAIL" -> R.string.error_community_post_delete_fail
            "COMMUNITY:POST_UPDATE_FAIL" -> R.string.error_community_post_update_fail
            "COMMUNITY:POST_CREATE_FAIL" -> R.string.error_community_post_create_fail
            "COMMUNITY:POST_REPLY_FAIL" -> R.string.error_community_post_reply_fail
            "COMMUNITY:POST_DETAIL_FAIL" -> R.string.error_community_post_detail_fail
            "COMMUNITY:COMMUNITY_LIST_FAIL" -> R.string.error_community_community_list_fail
            "COMMUNITY:POST_LIST_FAIL" -> R.string.error_community_post_list_fail
            "COMMUNITY:COMMUNITY_DETAIL_FAIL" -> R.string.error_community_community_detail_fail
            else -> {
                Timber.e(UndefinedKeyException("Undefined error key: $key"))
                R.string.error_unknown
            }
        }

        return context.getString(id)
    }

    companion object {
        const val KEY_INTERNAL_SERVER_ERROR = "SERVER:INTERNAL_SERVER_ERROR"
    }
}
