package com.example.cargo.recycle.detailfragmentRecycle

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
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

    fun bindIt(query: Query) {
        binding.apply {
            commentIconClick.text=getLikeOrAnswerVal(query)
            commentIconClick.setOnClickListener {
                commentIconClick.hide()
                profileImage.show()
                voiceImage.show()
                myTextEdit.show()
            }
            myTextEdit.doOnTextChanged { text, start, before, count ->
                Log.i(TAG, "bindIt: Text is -> $text")
                Log.i(TAG, "bindIt: Text is start by  -> $start")
                Log.i(TAG, "bindIt: Text is before by  -> $before")
                Log.i(TAG, "bindIt: Text is count -> $count")
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