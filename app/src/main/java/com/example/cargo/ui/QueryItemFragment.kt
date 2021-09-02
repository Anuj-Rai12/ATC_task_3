package com.example.cargo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cargo.R
import com.example.cargo.databinding.QueryItemFragmentBinding
import com.example.cargo.utils.DialogBox
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class QueryItemFragment : Fragment(R.layout.query_item_fragment) {
    private lateinit var binding: QueryItemFragmentBinding

    @Inject
    lateinit var dialogBox: DialogBox
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QueryItemFragmentBinding.bind(view)
        binding.locationClick.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        dialogBox.showDialog(requireActivity())
    }
}