package com.example.flixsterextended

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity"
private const val TOP_RATED_MOVIES = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val UPCOMING_MOVIES = "https://api.themoviedb.org/3/movie/upcoming?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private val upComingMovies = mutableListOf<UpComingMovie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        val rvUpcoming = findViewById<RecyclerView>(R.id.rvUpcomingMovies)
        val movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        val client = AsyncHttpClient()
        client.get(TOP_RATED_MOVIES, object : JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "On Failure")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "On Success")
                try {
                    val movieJSONArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJSONArray))
                    movieAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception error can be : $e")
                }
            }
        })
        val upcomingMovieAdapter = UpcomingAdapter(this,upComingMovies)
        rvUpcoming.adapter = upcomingMovieAdapter
        rvUpcoming.layoutManager = LinearLayoutManager(this)
        client.get(UPCOMING_MOVIES, object : JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "On Failure")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "On Success")
                try {
                    val movieJSONArrayUpcoming = json.jsonObject.getJSONArray("results")
                    upComingMovies.addAll(UpComingMovie.fromJsonArrayUp(movieJSONArrayUpcoming))
                    upcomingMovieAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception error can be : $e")
                }
            }
        })


    }
}