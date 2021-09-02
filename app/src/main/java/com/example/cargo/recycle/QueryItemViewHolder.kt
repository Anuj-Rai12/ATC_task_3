package com.example.cargo.recycle

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.cargo.TAG
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryItemBinding
import com.example.cargo.utils.getRanColor
import com.example.cargo.utils.setColor
import android.widget.TextView

import androidx.core.graphics.drawable.DrawableCompat


class QueryItemViewHolder(private val binding: QueryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private var bitmap: Bitmap? = null

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    fun bindIt(query: Query, itemClicked: (Query) -> Unit, context: Context) {
        binding.root.setOnClickListener {
            itemClicked(query)
        }
        Log.i(TAG, "bindIt: $query")

        binding.senderName.text = "${query.sender} . ${query.time}"
        binding.queryTitle.text = query.queryTxt
        changeBgColor(binding.senderProfileName)
        binding.senderProfileName.text = query.sender.first().toString()
        binding.senderProfileName.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = binding.menuIcon.id
        }
        query.queryComment.first().apply {
            binding.receiverProfileName.text = receiver.first().toString()
            changeBgColor(binding.receiverProfileName)
            binding.receiverAnswerTitle.text = receiverAnswerTxt
            binding.receiverName.text = "$receiver . $time"
        }
        binding.noOfLikes.text = "${query.likes} Likes . ${query.answer} Answer"
    }

    private fun changeBgColor(senderProfileName: TextView) {
        var buttonDrawable: Drawable? = senderProfileName.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        DrawableCompat.setTint(buttonDrawable, getRanColor())
        senderProfileName.background = buttonDrawable
    }
}

