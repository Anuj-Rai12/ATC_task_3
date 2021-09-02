package com.example.cargo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cargo.R
import com.example.cargo.databinding.QueryItemFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryItemFragment :Fragment(R.layout.query_item_fragment){
    private lateinit var binding: QueryItemFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= QueryItemFragmentBinding.bind(view)

    }

}