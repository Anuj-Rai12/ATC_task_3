package com.example.cargo.recycle.detailfragmentRecycle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryDetailLayoutBinding
import com.example.cargo.recycle.QueryItemAdaptor


class DetailAdaptor(private val context: Context) :
    ListAdapter<Query, DetailAdaptorViewHolder>(QueryItemAdaptor.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdaptorViewHolder {
        val binding =
            QueryDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailAdaptorViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: DetailAdaptorViewHolder, position: Int) {
        val current = getItem(position)
        current?.let {
            holder.bindIt(current)
        }
    }
}