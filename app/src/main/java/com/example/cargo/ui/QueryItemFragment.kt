package com.example.cargo.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.cargo.databinding.QueryItemFragmentBinding
import com.example.cargo.utils.DialogBox
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.app.AlertDialog
import com.example.cargo.R


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
        val view: View = layoutInflater.inflate(R.layout.custom_dialog_fragment, null)
        val infoDialog: AlertDialog = AlertDialog.Builder(activity)
            .setView(view)
            .create()
        val window = infoDialog.window
        val wlp: WindowManager.LayoutParams = window?.attributes!!
        wlp.gravity = Gravity.TOP
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.attributes = wlp
        infoDialog.show()
    }
}