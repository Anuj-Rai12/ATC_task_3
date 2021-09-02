package com.example.cargo.utils

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.cargo.databinding.CustomDialogFragmentBinding
import javax.inject.Inject

class DialogBox @Inject constructor()  {
    fun showDialog(context: Context){
        val binding = CustomDialogFragmentBinding.inflate(LayoutInflater.from(context))
        val infoDialog: AlertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        val window = infoDialog.window
        val wlp: WindowManager.LayoutParams = window?.attributes!!
        wlp.gravity = Gravity.TOP
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.attributes = wlp
        infoDialog.show()
    }
}