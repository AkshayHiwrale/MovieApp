package com.akshay.movieapp.data.model


import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<MovieResult>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)