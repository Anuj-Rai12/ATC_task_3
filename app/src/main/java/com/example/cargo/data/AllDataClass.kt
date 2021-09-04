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

sealed class AllDataClass {
    data class Images(
        val id:String,
        val bitmap: Bitmap?
    ) : AllDataClass()

    data class Question(
        val askedName: String,
        val time: String,
        val question: String,
        val likes: Int,
        val answer: Int
    ) : AllDataClass()

    data class Replied(
        val repliedBy: String,
        val time: String,
        val statement: String
    ) : AllDataClass()
}