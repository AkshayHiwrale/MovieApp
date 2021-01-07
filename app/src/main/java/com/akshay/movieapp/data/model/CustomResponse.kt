package com.akshay.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class CustomResponse<out T>(

@SerializedName("results")
var results: List<MovieResult>?


)