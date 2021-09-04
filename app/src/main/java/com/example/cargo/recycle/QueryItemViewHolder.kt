package com.example.cargo.recycle

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.cargo.R
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryItemBinding
import com.example.cargo.utils.getRanColor
import com.example.cargo.utils.hide
import com.example.cargo.utils.setColor
import com.example.cargo.utils.show


class QueryItemViewHolder(private val binding: QueryItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    private var bitmap: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    fun bindIt(query: Query, itemClicked: (Query) -> Unit) {
        binding.root.setOnClickListener {
            query.localBitmap = bitmap
            itemClicked(query)
        }
        if (query.queryImage == null) {
            binding.queryImg.hide()
            binding.senderProfileName.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.menuIcon.id
            }
            changeBgColor(binding.menuIcon, Color.TRANSPARENT)
        } else {
            binding.queryImg.show()
            binding.senderProfileName.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.queryImg.id
            }
            changeBgColor(binding.menuIcon, Color.BLACK)
            Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.myimage)
                .load(query.queryImage)
                .into(object :
                    CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmap = resource
                        binding.queryImg.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        placeholder?.let {
                            val anImage = (it as BitmapDrawable).bitmap
                            bitmap = anImage
                            binding.queryImg.setImageBitmap(anImage)
                        }
                    }
                })
        }
        binding.senderName.text = "${query.sender} . ${query.time}"
        binding.queryTitle.text = query.queryTxt
        changeBgColor(binding.senderProfileName, getRanColor(),3)
        binding.roundedSenderTextView.text = query.sender.first().toString()
        query.queryComment.first().apply {
            binding.roundedReceiverTextView.text = receiver.first().toString()
            changeBgColor(binding.receiverProfileName, getRanColor(),34)
            binding.receiverAnswerTitle.text = receiverAnswerTxt
            binding.receiverName.text = "$receiver . $time"
        }
        binding.noOfLikes.text = getLikeOrAnswerVal(query)
    }

    @SuppressLint("SetTextI18n")
    private fun getLikeOrAnswerVal(query: Query): String {
        return when {
            query.likes == 0 && query.answer != 0 -> "${query.answer} Answer"

            query.answer == 0 && query.likes != 0 -> {
                binding.commentIcon.text = "Comment"
                "${query.likes} Likes"
            }
            else -> {
                binding.commentIcon.text = "Answer"
                "${query.likes} Likes . ${query.answer} Answer"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeBgColor(senderProfileName: View, color: Int, choose: Int = 0) {
        val activity = context as Activity
        when (choose) {
            0 -> {
                var buttonDrawable: Drawable? = senderProfileName.background
                buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
                DrawableCompat.setTint(buttonDrawable, color)
                senderProfileName.background = buttonDrawable
            }
            else -> (senderProfileName as CardView).setCardBackgroundColor(
                activity.setColor(
                    getRanColor()
                )
            )
        }
    }
}

