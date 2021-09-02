package com.example.cargo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cargo.R
import com.example.cargo.databinding.QueryDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryDetailFragment :Fragment(R.layout.query_detail_fragment){
    private lateinit var binding: QueryDetailFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= QueryDetailFragmentBinding.bind(view)

    }
}