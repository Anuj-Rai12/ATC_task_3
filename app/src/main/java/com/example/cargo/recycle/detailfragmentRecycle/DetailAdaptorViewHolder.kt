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
import com.example.cargo.data.AllDataClass
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryDetailLayoutBinding
import com.example.cargo.recycle.detailfragmentRecycle.qeryalign.QueryDetailAdaptor
import com.example.cargo.utils.hide
import com.example.cargo.utils.rand
import com.example.cargo.utils.show

class DetailAdaptorViewHolder(
    private val binding: QueryDetailLayoutBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {
    private var seen1: Boolean? = null
    private var seen2: Boolean? = null
    private lateinit var queryDetailAdaptor: QueryDetailAdaptor
    private val dataItem = mutableListOf<AllDataClass>()
    private val enterAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.enter_anim)
    }
    private val popExitAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.pop_exit_anim)
    }

    fun bindIt(query: Query) {
        binding.apply {
            myRecycleView.apply {
                setHasFixedSize(true)
                queryDetailAdaptor =
                    QueryDetailAdaptor(context = this@DetailAdaptorViewHolder.context)
                adapter = queryDetailAdaptor
            }
            getData(query)

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
                    enterAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            Log.i(TAG, "onAnimationStart: Started Animation")
                            profileImage.hide()
                            myTextEdit.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                            }
                            locationIc.show()
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            Log.i(TAG, "onAnimationEnd: Ended Animation ")
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                } else if (seen1 == true && seen2 == null && count == 0) {
                    seen2 = true
                    seen1 = null
                    sendImage.startAnimation(popExitAnim)
                    popExitAnim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            locationIc.hide()
                            myTextEdit.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                bottomToBottom = binding.textViewRoot.id
                            }
                            profileImage.show()
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

    private fun getData(query: Query) {
        dataItem.add(AllDataClass.Images(bitmap = query.localBitmap, id = "Image.No ${rand(32)}"))
        dataItem.add(
            AllDataClass.Question(
                question = query.queryTxt,
                askedName = query.sender,
                time = query.time,
                likes = query.likes,
                answer = query.answer
            )
        )
        query.queryComment.forEach {
            dataItem.add(
                AllDataClass.Replied(
                    repliedBy = it.receiver,
                    time = it.time,
                    statement = it.receiverAnswerTxt
                )
            )
        }
        queryDetailAdaptor.submitList(dataItem)
    }

    @SuppressLint("SetTextI18n")
    private fun getLikeOrAnswerVal(query: Query): String {
        return when {
            query.answer == 0 && query.likes != 0 -> "Comment"
            else -> "Answer"
        }
    }
}