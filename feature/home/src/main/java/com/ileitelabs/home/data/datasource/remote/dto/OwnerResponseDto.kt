package com.ileitelabs.home.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class OwnerResponseDto(
    @SerializedName("login")
    val name: String?,
    @SerializedName("avatar_url")
    val img: String?,
)