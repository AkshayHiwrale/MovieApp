package com.akshay.movieapp.ui.home

import android.view.View
import com.akshay.movieapp.data.model.MovieResult

interface SelectedListener {

    fun selectedItem(item: MovieResult, view:View)
}