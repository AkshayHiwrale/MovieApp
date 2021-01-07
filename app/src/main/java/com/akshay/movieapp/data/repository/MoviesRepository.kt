package com.akshay.movieapp.data.repository

import com.akshay.movieapp.data.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val networkService: NetworkService){

     fun getMoviesList() = networkService.getMovieList()

     fun getMoviesDetails(movieId: Int) = networkService.getMovieDetails(movieId)
}