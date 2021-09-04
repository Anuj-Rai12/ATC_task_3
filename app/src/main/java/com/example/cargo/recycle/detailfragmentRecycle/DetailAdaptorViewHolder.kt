package com.example.cargo.recycle.detailfragmentRecycle

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cargo.R
import com.example.cargo.TAG
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryDetailLayoutBinding
import com.example.cargo.utils.hide
import com.example.cargo.utils.show

class DetailAdaptorViewHolder(
    private val binding: QueryDetailLayoutBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {
    private var seen1: Boolean? = null
    private var seen2: Boolean? = null
    fun bindIt(query: Query) {

        val botTopAnim = AnimationUtils.loadAnimation(context, R.anim.bot_top_anim)
        val enterAnim = AnimationUtils.loadAnimation(context, R.anim.enter_anim)
        val exitAnim = AnimationUtils.loadAnimation(context, R.anim.exit_anim)
        val popExitAnim = AnimationUtils.loadAnimation(context, R.anim.pop_exit_anim)
        val popEnterAnim = AnimationUtils.loadAnimation(context, R.anim.pop_enter_anim)


        binding.apply {
            commentIconClick.text = getLikeOrAnswerVal(query)
            commentIconClick.setOnClickListener {
                commentIconClick.hide()
                profileImage.show()
                voiceImage.show()
                myTextEdit.show()
            }
            myTextEdit.doOnTextChanged { _, _, _, count ->
                if (count == 1 && seen1 == null) {
                    sendImage.show()
                    seen1 = true
                    seen2 = null
                    sendImage.startAnimation(enterAnim)
                    profileImage.startAnimation(exitAnim)
                    exitAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            Log.i(TAG, "onAnimationStart: Started Animation")
                            myTextEdit.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                            }
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            Log.i(TAG, "onAnimationEnd: Ended Animation ")
                            locationIc.show()
                            locationIc.startAnimation(botTopAnim)
                            profileImage.hide()
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                } else if (seen1 == true && seen2 == null && count == 0) {
                    seen2 = true
                    seen1 = null
                    profileImage.show()
                    sendImage.startAnimation(popExitAnim)
                    profileImage.startAnimation(popEnterAnim)
                    popEnterAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            myTextEdit.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                bottomToBottom = binding.textViewRoot.id
                            }
                            locationIc.hide()
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            sendImage.hide()
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}

                    })
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getLikeOrAnswerVal(query: Query): String {
        return when {
            query.answer == 0 && query.likes != 0 -> "Comment"
            else -> "Answer"
        }
    }
}