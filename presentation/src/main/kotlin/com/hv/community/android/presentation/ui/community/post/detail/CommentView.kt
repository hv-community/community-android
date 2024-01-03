package com.hv.community.android.presentation.ui.community.post.detail

import android.animation.ValueAnimator
import android.content.Context
import android.text.method.KeyListener
import android.text.method.MovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.widget.doAfterTextChanged
import com.hv.community.android.common.ifZero
import com.hv.community.android.common.orFalse
import com.hv.community.android.common.orZero
import com.hv.community.android.presentation.common.util.hideKeyboard
import com.hv.community.android.presentation.common.util.showKeyboard
import com.hv.community.android.presentation.common.view.OnStateChangedListener
import com.hv.community.android.presentation.common.view.OnTextChangedListener
import com.hv.community.android.presentation.databinding.ViewCommentBinding
import com.ray.rds.R

class CommentView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {
    private val binding = ViewCommentBinding.inflate(LayoutInflater.from(context), this, true)

    var content: String? = null
        set(value) {
            field = value
            refreshComment()
        }

    var nickname: String? = null
        set(value) {
            field = value
            refreshComment()
        }

    var isPasswordVisible: Boolean? = null
        set(value) {
            field = value
            refreshControl()
        }

    var password: String? = null
        set(value) {
            field = value
            refreshControl()
        }

    var fixedContent: String? = null
        set(value) {
            field = value
            refreshControl()
        }

    var isExpanded: Boolean?
        get() = isExpandedInternal
        set(value) {
            isExpandedInternal = value.orFalse()
            refreshControl()
        }

    var isEditing: Boolean?
        get() = isEditingInternal
        set(value) {
            isEditingInternal = value.orFalse()
            refreshView()
        }

    var isDeleting: Boolean? = null
        set(value) {
            field = value
            refreshControl()
        }

    var isExpandEnabled: Boolean? = null
        set(value) {
            field = value
            refreshComment()
        }

    var onEditClick: OnClickListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    var onDeleteClick: OnClickListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    var onPasswordChanged: OnTextChangedListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    var onContentChanged: OnTextChangedListener? = null
        set(value) {
            field = value
            refreshComment()
        }

    var onExpand: OnStateChangedListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    var onEditing: OnStateChangedListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    var onDeleting: OnStateChangedListener? = null
        set(value) {
            field = value
            refreshControl()
        }

    private var isExpandedInternal: Boolean = false

    private var isEditingInternal: Boolean = false

    private var movementMethod: MovementMethod? = null

    private var keyListener: KeyListener? = null

    private val controlAnimator: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = context.resources.getInteger(R.integer.animation_duration).toLong()
            interpolator = DecelerateInterpolator()
            addListener(
                onEnd = {
                    binding.containerControl.updateLayoutParams {
                        height = if (isExpandedInternal) LayoutParams.WRAP_CONTENT else 1
                    }
                }
            )
            addUpdateListener {
                val animatedValue = if (isExpandedInternal) it.animatedValue as Float else 1f - it.animatedValue as Float

                binding.containerControl.measure(
                    MeasureSpec.makeMeasureSpec(binding.containerControl.width, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                val animatedHeight: Int = (binding.containerControl.measuredHeight * animatedValue).toInt().ifZero { 1 }

                binding.containerControl.updateLayoutParams {
                    height = animatedHeight
                }
            }
        }
    }

    init {
        initView()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        refreshView()
    }

    private fun initView() {
        binding.containerControl.updateLayoutParams {
            height = if (isExpandedInternal) LayoutParams.WRAP_CONTENT else 1
        }
    }

    private fun refreshView() {
        refreshComment()
        refreshControl()
    }

    private fun refreshComment() {
        binding.containerComment.setOnClickListener {
            if (isExpandEnabled == true) {
                switchExpand()
            }
        }
        binding.containerComment.isClickable = isExpandEnabled == true

        binding.nickname.text = "작성자 : $nickname"
        binding.content.setText(
            if (isEditingInternal) fixedContent else content
        )
//        binding.content.removeTextChangedListener() TODO
        binding.content.doAfterTextChanged {
            onContentChanged?.onChanged(it.toString())
        }
        binding.content.isEnabled = isEditingInternal
        if (!isEditingInternal && binding.content.movementMethod != null) movementMethod = binding.content.movementMethod
        if (!isEditingInternal && binding.content.keyListener != null) keyListener = binding.content.keyListener

        binding.content.movementMethod = if (isEditingInternal) movementMethod else null
        binding.content.keyListener = if (isEditingInternal) keyListener else null
    }

    private fun refreshControl() {
        binding.password.isVisible = (isPasswordVisible == true)
        binding.password.setText(password)
//        binding.password.removeTextChangedListener() TODO
        binding.password.doAfterTextChanged {
            onPasswordChanged?.onChanged(it.toString())
        }

        binding.containerCancel.setOnClickListener {
            when {
                isEditing == true -> {
                    switchEditMode()
                }

                isDeleting == true -> {
                    switchDeleteMode()
                }

                else -> {
                    switchEditMode()
                }
            }
        }
        binding.containerConfirm.setOnClickListener {
            when {
                isEditing == true -> {
                    switchEditMode()
                    onEditClick?.onClick(it)
                }

                isDeleting == true -> {
                    switchDeleteMode()
                    onDeleteClick?.onClick(it)
                }

                else -> {
                    switchDeleteMode()
                }
            }
        }
        binding.containerConfirm.setCardBackgroundColor(
            if (isEditing == true || isDeleting == true) {
                context.getColor(R.color.blue_50)
            } else {
                context.getColor(R.color.blue_gray_50)
            }
        )
        binding.textCancel.text = if (isEditing == true || isDeleting == true) "취소" else "수정"
        binding.textConfirm.text = if (isEditing == true || isDeleting == true) "확인" else "삭제"
        binding.containerConfirm.clearAnimation()
        binding.arrow.isInvisible = (isExpandEnabled != true)
        binding.arrow.clearAnimation()
    }

    private fun switchExpand() {
        isExpandedInternal = !isExpandedInternal

        val duration = context.resources.getInteger(R.integer.animation_duration).toLong()
        val arrowRotationAngle = if (isExpandedInternal) -180f else 0f

        binding.arrow.animate()
            .rotation(arrowRotationAngle)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()

        controlAnimator.start()
        onExpand?.onChanged(isExpandedInternal)
    }

    private fun switchEditMode() {
        isEditingInternal = !isEditingInternal
        refreshView()

        post {
            if (isEditingInternal) {
                binding.content.setSelection(0, binding.content.text?.length.orZero())
                binding.content.showKeyboard()
            } else {
                post {
                    binding.content.hideKeyboard()
                }
            }
        }
        onEditing?.onChanged(isEditing.orFalse())
    }

    private fun switchDeleteMode() {
        isDeleting = isDeleting != true
        refreshView()
        onDeleting?.onChanged(isDeleting.orFalse())
    }
}
