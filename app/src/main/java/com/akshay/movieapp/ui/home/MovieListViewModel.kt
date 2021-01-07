package com.akshay.movieapp.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshay.movieapp.data.model.MovieDetails
import com.akshay.movieapp.data.model.MovieResult
import com.akshay.movieapp.data.repository.MoviesRepository
import com.akshay.movieapp.utils.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel @ViewModelInject constructor(
    private val moviesRepository: MoviesRepository

) : ViewModel() {
    val moviesList: MutableLiveData<Resource<MutableList<MovieResult>>> = MutableLiveData()
    val moviesDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()


    fun fetchMovieDetails(movieId: Int) {

        moviesDetails.postValue(Resource.loading())
        try {
            compositeDisposable.add(
                moviesRepository.getMoviesDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            moviesDetails.postValue(Resource.success(it))
                        },
                        {
                            moviesDetails.postValue(Resource.error())
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
                        }
                    )
            )

        } catch (e: Exception) {
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }


    }
    fun getMovies() {

        moviesList.postValue(Resource.loading())
        try {
            compositeDisposable.add(
                moviesRepository.getMoviesList()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            moviesList.postValue(Resource.success(it.results?.toMutableList()))
                        },
                        {
                            moviesList.postValue(Resource.error())
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
                        }
                    )
            )

        } catch (e: Exception) {
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }


    }


}