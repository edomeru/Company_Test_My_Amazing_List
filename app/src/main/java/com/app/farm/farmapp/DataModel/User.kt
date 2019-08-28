package com.app.farm.farmapp.DataModel


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("post_pic")
    val postPic: String,
    val name: String
)