package com.example.cargo.recycle


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cargo.data.Query
import com.example.cargo.databinding.QueryItemBinding

class QueryItemAdaptor(private val context: Context,private val itemClicked: (Query) -> Unit) : ListAdapter<Query, QueryItemViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Query>() {
            override fun areItemsTheSame(oldItem: Query, newItem: Query): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Query, newItem: Query): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryItemViewHolder {
        val binding= QueryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QueryItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: QueryItemViewHolder, position: Int) {
        val curr=getItem(position)
        curr?.let {
            holder.bindIt(query = it,itemClicked = itemClicked,context=context)
        }
    }

}