package com.akshay.movieapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akshay.movieapp.R
import com.akshay.movieapp.data.model.MovieResult
import com.akshay.movieapp.ui.details.MovieDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),SelectedListener {
    val movieListViewModel: MovieListViewModel by viewModels()
    lateinit var adapter: MovieListAdapter
    lateinit var adapterHeader: MovieHeaderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initViews()
        initObservers()
        movieListViewModel.getMovies()
    }

    private fun initViews() {
        initMovieHeader()
        initMovieListAdapter()

    }

    private fun initMovieHeader() {
        adapterHeader = MovieHeaderListAdapter(this)
        rv_movie_list_header.adapter = adapterHeader
        rv_movie_list_header.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun initMovieListAdapter() {
        adapter = MovieListAdapter(this,this)
        rv_movie_list.adapter = adapter
        rv_movie_list.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
    }

    private fun initObservers() {
        movieListViewModel.moviesList.observe(this, Observer {
            it.data?.let { it1 ->
                run {
                    adapter.setWords(it1)
                    adapterHeader.setWords(it1)

                }
            }
        })

    }

    override fun selectedItem(item: MovieResult, view:View) {
        val intent: Intent = Intent(MainActivity@this,
            MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", item.id)
        intent.putExtra("MOVIE_IMG_URL", item.posterPath)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val activityOptionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "imageMain")
          startActivity(intent , activityOptionsCompat.toBundle())

        }else{
           startActivity(intent)
        }
    }
}