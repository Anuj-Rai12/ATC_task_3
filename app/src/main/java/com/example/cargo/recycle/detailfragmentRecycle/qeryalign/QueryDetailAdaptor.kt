package com.example.cargo.recycle.detailfragmentRecycle.qeryalign

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cargo.R
import com.example.cargo.data.AllDataClass
import com.example.cargo.databinding.QueryCommentItemBinding
import com.example.cargo.databinding.QueryImageItemBinding
import com.example.cargo.databinding.QueyAskItemBinding

class QueryDetailAdaptor constructor(private val context: Context) :
    ListAdapter<AllDataClass, AllViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
        return when (viewType) {
            R.layout.query_comment_item -> {
                AllViewHolder.QueryAnswerType(
                    binding = QueryCommentItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context = context
                )
            }
            R.layout.quey_ask_item -> {
                AllViewHolder.QueryAskQuestion(
                    binding = QueyAskItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context = context
                )
            }
            R.layout.query_image_item -> {
                AllViewHolder.QueryImageViewHolder(
                    binding = QueryImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context = context
                )
            }
            else -> throw  IllegalArgumentException("No Layout Found Anuj")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AllDataClass.Images -> R.layout.query_image_item
            is AllDataClass.Question -> R.layout.quey_ask_item
            is AllDataClass.Replied -> R.layout.query_comment_item
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        val current = getItem(position)
        current?.let { curr ->
            when (holder) {
                is AllViewHolder.QueryAnswerType -> holder.bindIt(curr as AllDataClass.Replied)
                is AllViewHolder.QueryAskQuestion -> holder.bindIt(curr as AllDataClass.Question)
                is AllViewHolder.QueryImageViewHolder -> holder.bindIt(curr as AllDataClass.Images)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AllDataClass>() {
            override fun areItemsTheSame(oldItem: AllDataClass, newItem: AllDataClass): Boolean {
                return getValue(oldItem) == getValue(newItem)
            }

            private fun getValue(oldItem: AllDataClass): String {
                return when (oldItem) {
                    is AllDataClass.Images -> oldItem.id
                    is AllDataClass.Question -> oldItem.question
                    is AllDataClass.Replied -> oldItem.statement
                }
            }

            override fun areContentsTheSame(oldItem: AllDataClass, newItem: AllDataClass): Boolean {
                return oldItem == newItem
            }
        }
    }
}