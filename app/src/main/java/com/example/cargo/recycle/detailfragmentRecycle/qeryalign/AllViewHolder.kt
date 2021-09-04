package com.example.cargo.recycle.detailfragmentRecycle.qeryalign

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cargo.R
import com.example.cargo.data.AllDataClass
import com.example.cargo.databinding.QueryCommentItemBinding
import com.example.cargo.databinding.QueryImageItemBinding
import com.example.cargo.databinding.QueyAskItemBinding
import com.example.cargo.utils.*

sealed class AllViewHolder(viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    class QueryImageViewHolder(
        private val binding: QueryImageItemBinding,
        private val context: Context
    ) :
        AllViewHolder(binding) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bindIt(query: AllDataClass.Images) {
            if (query.bitmap != null) {
                binding.queryImg.show()
                changeBgColor(binding.closeBtnClick, R.color.black)
                changeBgColor(binding.menuBtnClick, R.color.black)
                binding.queryImg.setImageBitmap(query.bitmap)
            } else {
                binding.queryImg.hide()
                changeBgColor(binding.closeBtnClick, R.color.white)
                changeBgColor(binding.menuBtnClick, R.color.white)
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun changeBgColor(cardView: CardView, color: Int) {
            val activity = context as Activity
            cardView.setCardBackgroundColor(activity.setColor(color))
        }
    }

    class QueryAskQuestion(private val binding: QueyAskItemBinding, private val context: Context) :
        AllViewHolder(binding) {
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bindIt(query: AllDataClass.Question) {
            binding.apply {
                SenderTextView.text = query.askedName.first().toString()
                changeBgColor(senderProfileNameSelected, getRanColor())
                senderNameSelected.text = "${query.askedName} . ${query.time}"
                queryTitleSelected.text = query.question
                noOfLikesSelected.text = getLikeOrAnswerVal(query)
            }
        }

        @SuppressLint("SetTextI18n")
        private fun getLikeOrAnswerVal(query: AllDataClass.Question): String {
            return when {
                query.likes == 0 && query.answer != 0 -> "${query.answer} Answer"

                query.answer == 0 && query.likes != 0 -> {
                    binding.commentIconSelected.text = "Comment"
                    "${query.likes} Likes"
                }
                else -> {
                    binding.commentIconSelected.text = "Answer"
                    "${query.likes} Likes . ${query.answer} Answer"
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun changeBgColor(cardView: CardView, color: Int) {
            val activity = context as Activity
            cardView.setCardBackgroundColor(activity.setColor(color))
        }
    }

    class QueryAnswerType(
        private val binding: QueryCommentItemBinding,
        private val context: Context
    ) : AllViewHolder(binding) {
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.M)
        fun bindIt(query: AllDataClass.Replied) {
            binding.apply {
                roundedReceiverTextView.text = query.repliedBy.first().toString()
                receiverName.text = "${query.repliedBy} . ${query.time}"
                receiverAnswerTitle.text = query.statement
                rand(5).apply {
                    likeCommentIcon.text = if (this > 0) this.toString() else "1"
                }
                changeBgColor(receiverProfileName, getRanColor())
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun changeBgColor(cardView: CardView, color: Int) {
            val activity = context as Activity
            cardView.setCardBackgroundColor(activity.setColor(color))
        }
    }
}
