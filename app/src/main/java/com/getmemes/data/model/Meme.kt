package com.getmemes.data.model

import com.google.gson.annotations.SerializedName

data class GetMemes(
    @SerializedName("data")
    val data: Data? = null
)

data class Data(
    @SerializedName("memes")
    val memes: List<Meme> = listOf()
)

data class Meme(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""
)