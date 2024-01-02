package com.hv.community.android.presentation.common.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.hv.community.android.presentation.R
import com.hv.community.android.presentation.databinding.ViewActionBarBinding
import com.hv.community.android.presentation.common.util.getColorStateList
import com.hv.community.android.presentation.common.util.getDrawable
import com.ray.rds.util.getBoolean
import com.ray.rds.util.getString

class ActionBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {
    private val binding = ViewActionBarBinding.inflate(LayoutInflater.from(context), this, true)

    var text: CharSequence? = null
        set(value) {
            field = value
            refreshTitle()
        }

    var textColor: ColorStateList? = null
        set(value) {
            field = value
            refreshTitle()
        }

    var isBackVisible: Boolean? = null
        set(value) {
            field = value
            refreshBack()
        }

    var isBackEnabled: Boolean? = null
        set(value) {
            field = value
            refreshBack()
        }

    var isSideVisible: Boolean? = null
        set(value) {
            field = value
            refreshSide()
        }

    var isSideEnabled: Boolean? = null
        set(value) {
            field = value
            refreshSide()
        }

    var sideIcon: Drawable? = null
        set(value) {
            field = value
            refreshSide()
        }

    var sideTintColor: ColorStateList? = null
        set(value) {
            field = value
            refreshSide()
        }

    var onBackClick: OnClickListener? = null
        set(value) {
            field = value
            refreshBack()
        }

    var onSideClick: OnClickListener? = null
        set(value) {
            field = value
            refreshSide()
        }

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarView).use { attributes ->
            attributes.getString(R.styleable.ActionBarView_android_text) { text ->
                this.text = text
            }
            attributes.getColorStateList(R.styleable.ActionBarView_android_textColor) { textColor ->
                this.textColor = textColor
            }
            attributes.getBoolean(R.styleable.ActionBarView_isBackEnabled) { isBackEnabled ->
                this.isBackEnabled = isBackEnabled
            }
            attributes.getBoolean(R.styleable.ActionBarView_isSideEnabled) { isSideEnabled ->
                this.isSideEnabled = isSideEnabled
            }
            attributes.getBoolean(R.styleable.ActionBarView_isBackVisible) { isBackVisible ->
                this.isBackVisible = isBackVisible
            }
            attributes.getBoolean(R.styleable.ActionBarView_isSideVisible) { isSideVisible ->
                this.isSideVisible = isSideVisible
            }
            attributes.getDrawable(R.styleable.ActionBarView_sideIcon) { sideIcon ->
                this.sideIcon = sideIcon
            }
            attributes.getColorStateList(R.styleable.ActionBarView_sideTintColor) { sideTintColor ->
                this.sideTintColor = sideTintColor
            }
        }

        refreshView()
    }

    private fun refreshView() {
        refreshTitle()
        refreshBack()
        refreshSide()
    }

    private fun refreshTitle() {
        with(binding) {
            title.text = text
            textColor?.let { title.setTextColor(it) }
        }
    }

    private fun refreshBack() {
        with(binding) {
            back.isEnabled = isBackEnabled == true
            back.isVisible = isBackVisible == true
            back.setOnClickListener(onBackClick)
        }
    }

    private fun refreshSide() {
        with(binding) {
            side.isEnabled = isSideEnabled == true
            side.isVisible = isSideVisible == true
            side.setImageDrawable(sideIcon)
            sideTintColor?.let { side.imageTintList = it }
            side.setOnClickListener(onSideClick)
        }
    }
}
