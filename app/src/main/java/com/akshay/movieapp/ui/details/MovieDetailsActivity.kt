package com.akshay.movieapp.ui.details

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.akshay.movieapp.R
import com.akshay.movieapp.data.Networking
import com.akshay.movieapp.ui.home.MovieListViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_details_layout.*

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private val movieListViewModel: MovieListViewModel by viewModels()
    var movieId: Int? = null
    var movieUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_layout)
        init()
    }

    private fun init() {
        var intent = this.intent
        if (intent.hasExtra("MOVIE_ID")) {
            movieId = intent.getIntExtra("MOVIE_ID", 464052)
        }
        if (intent.hasExtra("MOVIE_IMG_URL")) {
            movieUrl = intent.getStringExtra("MOVIE_IMG_URL")
        }
        initViews()
        initObservers()
        movieId?.let { movieListViewModel.fetchMovieDetails(it) }
    }

    private fun initViews() {
        val moviePosterURL = Networking.POSTER_BASE_URL + movieUrl
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_poster)
        ObjectAnimator.ofFloat(layout_details, "translationX", -35f).apply {
            duration = 1000
            start()
        }

    }


    private fun initObservers() {
        movieListViewModel.moviesDetails.observe(this, Observer {
            it.data?.let { it1 ->
                title_view.text = it.data.title
                title_movie.text = it.data.title
                tv_discription.text = it.data.overview
                title_movie_data.text = it.data.releaseDate

            }
        })

    }
}