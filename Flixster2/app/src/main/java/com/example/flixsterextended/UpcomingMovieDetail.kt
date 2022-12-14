package com.example.flixsterextended

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class UpcomingMovieDetail : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var voteCount: TextView
    private lateinit var voteAverage: RatingBar
    private lateinit var posterDetail: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_detail)
        tvTitle = findViewById(R.id.tvTitleDetailUp)
        tvOverview = findViewById(R.id.tvOverviewDetailUp)
        tvReleaseDate = findViewById(R.id.tvReleaseDateDetailUp)
        voteCount = findViewById(R.id.tvVotesUpDetail)
        voteAverage = findViewById(R.id.rbVoteAverageUp)
        posterDetail = findViewById(R.id.ivPosterDetailUp)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA_COMING) as UpComingMovie
        tvTitle.text = movie.upMovieTitle
        tvOverview.text = movie.upMovieOverview
        tvReleaseDate.text =  "Release Date: " + movie.upReleaseDate
        voteCount.text = movie.upVoteCount.toString()
        voteAverage.rating = movie.upVoteAverage.toFloat()
        val imageUrl = movie.posterImageUrlUpcoming
        Glide.with(this).load(imageUrl).centerCrop().transforms(RoundedCorners(140)).into(posterDetail)
    }
}