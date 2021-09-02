package com.example.cargo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.example.cargo.R
import com.example.cargo.TAG
import com.example.cargo.databinding.QueryDetailFragmentBinding
import com.example.cargo.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryDetailFragment : Fragment(R.layout.query_detail_fragment) {
    private lateinit var binding: QueryDetailFragmentBinding
    private val appViewModel: AppViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QueryDetailFragmentBinding.bind(view)
        Log.i(TAG, "onViewCreated: Other Layout ${appViewModel.itemClickedData}")
    }
}