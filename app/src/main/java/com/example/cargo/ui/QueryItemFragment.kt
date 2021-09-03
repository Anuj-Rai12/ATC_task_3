package com.example.cargo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cargo.R
import com.example.cargo.TAG
import com.example.cargo.databinding.QueryItemFragmentBinding
import com.example.cargo.recycle.QueryItemAdaptor
import com.example.cargo.utils.DialogBox
import com.example.cargo.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class QueryItemFragment : Fragment(R.layout.query_item_fragment) {
    private lateinit var binding: QueryItemFragmentBinding
    private val appViewModel: AppViewModel by viewModels()
    private var queryItemAdaptor: QueryItemAdaptor? = null

    @Inject
    lateinit var dialogBox: DialogBox
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QueryItemFragmentBinding.bind(view)

        binding.MainRecycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            queryItemAdaptor = QueryItemAdaptor(requireActivity()) {
                Log.i(TAG, "onViewCreated: $it")
                val action=QueryItemFragmentDirections.actionQueryItemFragmentToQueryDetailFragment(it)
                findNavController().navigate(action)
            }
            adapter=queryItemAdaptor
        }
        queryItemAdaptor?.submitList(appViewModel.data)

        binding.locationClick.setOnClickListener {
            dialogBox.showDialog(requireActivity())
        }
    }
}