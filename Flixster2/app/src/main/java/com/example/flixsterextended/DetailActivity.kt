package com.example.flixsterextended

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private  lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var voteCount: TextView
    private lateinit var voteAverage: RatingBar
    private lateinit var posterDetail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        tvTitle = findViewById(R.id.tvTitleDetail)
        tvOverview = findViewById(R.id.tvOverviewDetail)
        tvReleaseDate = findViewById(R.id.tvReleaseDateDetail)
        voteCount = findViewById(R.id.tvVotes)
        voteAverage = findViewById(R.id.rbVoteAverage)
        posterDetail = findViewById(R.id.ivPosterDetail)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        tvTitle.text = movie.movieTitle
        tvOverview.text = movie.movieOverview
        tvReleaseDate.text =  "Release Date: " + movie.releaseDate
        voteCount.text = movie.voteCount.toString()
        voteAverage.rating = movie.voteAverage.toFloat()
        val imageUrl = movie.posterImageUrl
        Glide.with(this).load(imageUrl).centerCrop().transforms(RoundedCorners(140)).into(posterDetail)
    }
}