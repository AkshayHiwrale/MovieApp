package com.akshay.movieapp.data

import com.akshay.movieapp.data.model.CustomResponse
import com.akshay.movieapp.data.model.MovieDetails
import com.akshay.movieapp.data.model.MoviesList
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.GET_MOVIE_LIST)
     fun getMovieList(): Single<MoviesList>


    @GET(Endpoints.GET_MOVIE_DETAILS)
     fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<MovieDetails>
}