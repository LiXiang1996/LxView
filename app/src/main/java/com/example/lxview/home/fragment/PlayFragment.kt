package com.example.lxview.home.fragment

import android.animation.ObjectAnimator
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.baselib.utils.ToastUtils
import com.example.lxview.R
import com.example.lxview.base.fragment.BaseFragment

/**
 * author: 李 祥
 * date:   2022/3/31 3:40 下午
 * description:
 */
class PlayFragment : BaseFragment() {
    override val contentId: Int
        get() = R.layout.fg_play
    private var iconDrawable: AppCompatImageView? = null
    private var changeButton: Button? = null
    private var isOpenPhone = false
    private var editPhone: LinearLayout? = null
    private var container: ConstraintLayout? = null
    var offsetAn = ObjectAnimator.ofInt(0)

    override fun initView() {
        iconDrawable = mRootView.findViewById(R.id.fg_play_img)
        changeButton = mRootView.findViewById(R.id.button_change)
        editPhone = mRootView.findViewById(R.id.edit_container_phone)
        container = mRootView.findViewById(R.id.edit_container_rl)
        offsetAn.duration = 300
        offsetAn.addUpdateListener {
            val offset = it.animatedValue as Int
            container?.scrollTo(offset, 0)
        }
    }

    override fun initListener() {
        iconDrawable?.setOnClickListener {
            context?.let { it1 -> ToastUtils.showToast(it1, "你看你妈呢？") }
        }
        changeButton?.setOnClickListener {
            if (container?.scrollX == 0) {
                offsetAn.setIntValues(0, editPhone?.width!!)
            } else {
                offsetAn.setIntValues(editPhone?.width!!, 0)
            }
            offsetAn.start()
        }

    }


    private fun showAnimation(view: View) {
        view.visibility = View.VISIBLE
        val spring = SpringForce(0f).setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY).setStiffness(SpringForce.STIFFNESS_MEDIUM)
        val anim = SpringAnimation(view, SpringAnimation.TRANSLATION_X).setSpring(spring)
        anim.cancel()
        anim.setStartValue(-view.width.toFloat())
        anim.start()
        anim.addEndListener { _, _, _, _ ->
        }
    }

    private fun closeAnimation(view: View) {
        view.visibility = View.VISIBLE
        val spring = SpringForce(-view.width.toFloat()).setDampingRatio(SpringForce.DAMPING_RATIO_NO_BOUNCY).setStiffness(SpringForce.STIFFNESS_MEDIUM)
        val anim = SpringAnimation(view, SpringAnimation.TRANSLATION_X).setSpring(spring)
        anim.cancel()
        anim.setStartValue(0f)
        anim.start()
        anim.addEndListener { _, _, _, _ ->
            view.visibility = View.GONE
        }
    }
}