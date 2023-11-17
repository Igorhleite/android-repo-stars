package com.ileitelabs.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GitResponseDomain(
    @SerializedName("items")
    val repositories: List<RepositoriesDomain>?,
)