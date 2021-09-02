package com.example.cargo.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.cargo.TAG
import com.example.cargo.databinding.CustomDialogFragmentBinding
import javax.inject.Inject

class DialogBox @Inject constructor() : DialogFragment() {
    private lateinit var binding: CustomDialogFragmentBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CustomDialogFragmentBinding.inflate(layoutInflater)
        val alertDialog =
            AlertDialog.Builder(requireActivity()).setView(binding.root)

        return alertDialog.create()
    }
}