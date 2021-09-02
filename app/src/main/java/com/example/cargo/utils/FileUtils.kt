package com.example.cargo.utils

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.cargo.R
import kotlin.random.Random

fun rand(to: Int): Int {
    val from = 0
    return Random.nextInt(to - from) + from
}

fun getRanColor(): Int {
    val colors = listOf(R.color.app_color,
            R.color.app_btn_color,
            R.color.app_color_text,
            R.color.purple_500,
            R.color.orange,
            R.color.light_blue,
            R.color.grey,
            R.color.purple_700
    )
    val position = rand(colors.size)
    return colors[position]
}

fun getRandImage(): String {
    val images = listOf("https://images.unsplash.com/photo-1533387520709-752d83de3630?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80",
            "https://images.unsplash.com/photo-1599409522056-e7fab1dadfec?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=358&q=80",
            "https://images.unsplash.com/photo-1470252649378-9c29740c9fa8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"
    )
    val position = rand(images.size)
    return images[position]
}

fun View.show(){
    this.isVisible=true
}
fun View.hide(){
    this.isVisible=false
}
@RequiresApi(Build.VERSION_CODES.M)
fun Activity.setColor(color:Int): Int {
    return resources.getColor(color,null)
}