package com.vlv.githubapicompose.data.remote


import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepositoryResponse>,
    @SerializedName("total_count")
    val totalCount: Int
)