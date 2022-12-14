package com.example.flixsterextended

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie(
    val movieId: Int,
    private val posterPath:String,
    val movieTitle:String,
    val movieOverview: String,
    val releaseDate: String,
    val voteCount: Int,
    val voteAverage: Number
    ) : Parcelable {
        @IgnoredOnParcel
        val posterImageUrl = "https://image.tmdb.org/t/p/w500/$posterPath"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("release_date"),
                        movieJson.getInt("vote_count"),
                        movieJson.getDouble("vote_average")

                    )
                )
            }
            return movies
        }

    }
    }

@Parcelize
data class UpComingMovie(
    val upMovieId: Int,
    private val upPosterPath:String,
    val upMovieTitle:String,
    val upMovieOverview: String,
    val upReleaseDate: String,
    val upVoteCount: Int,
    val upVoteAverage: Number
) : Parcelable {
    @IgnoredOnParcel
    val posterImageUrlUpcoming = "https://image.tmdb.org/t/p/w500/$upPosterPath"
    companion object {
        fun fromJsonArrayUp(movieJsonArray: JSONArray): MutableList<UpComingMovie> {
            val upComingMovies = mutableListOf<UpComingMovie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                upComingMovies.add(
                    UpComingMovie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("release_date"),
                        movieJson.getInt("vote_count"),
                        movieJson.getDouble("vote_average")

                    )
                )
            }
            return upComingMovies
        }

    }

}

