package com.akshay.movieapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.akshay.movieapp.R
import com.akshay.movieapp.data.Networking
import com.akshay.movieapp.data.model.MovieResult
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class MovieHeaderListAdapter internal constructor(
    var context: Context
) : RecyclerView.Adapter<MovieHeaderListAdapter.WordViewHolder>() {
    private val inflater:
            LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<MovieResult>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_movie_header, parent, false)
        return WordViewHolder(itemView)
    }


    override fun getItemCount(): Int {
      return  words.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]

        val moviePosterURL = Networking.POSTER_BASE_URL + current.posterPath
        moviePosterURL?.let {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
            Glide.with(context)
                .load(moviePosterURL)
                .apply(requestOptions)
                .into(holder.ivMovie)
        }

    }
    internal fun setWords(words: List<MovieResult>) {
        this.words = words
        notifyDataSetChanged()
    }

}