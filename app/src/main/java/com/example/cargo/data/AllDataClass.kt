package com.example.cargo.data

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val id: Int,
    val sender: String,
    val queryImage: String? = null,
    val queryTxt: String,
    val answer: Int,
    val likes: Int,
    var localBitmap: Bitmap? = null,
    val queryComment: List<QueryComment>,
    val time: String
) : Parcelable

@Parcelize
data class QueryComment(
    val receiver: String,
    val time: String,
    val receiverAnswerTxt: String
) : Parcelable