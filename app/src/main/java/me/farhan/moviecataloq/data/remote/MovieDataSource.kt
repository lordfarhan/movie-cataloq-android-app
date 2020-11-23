package me.farhan.moviecataloq.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.network.ApiService
import me.farhan.moviecataloq.network.response.TmdbResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author farhan
 * created at at 22:34 on 14/11/20.
 */
class MovieDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: MovieDataSource? = null

        fun getInstance(apiService: ApiService): MovieDataSource =
            instance ?: synchronized(this) {
                instance ?: MovieDataSource(apiService)
            }
    }

    fun fetchMovies(): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        apiService.getPopularMovies(BuildConfig.API_KEY)
            .enqueue(object : Callback<TmdbResponse<Movie>> {
                override fun onResponse(
                    call: Call<TmdbResponse<Movie>>,
                    response: Response<TmdbResponse<Movie>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            movies.postValue(it.results)
                        }
                    }
                }

                override fun onFailure(call: Call<TmdbResponse<Movie>>, t: Throwable) {

                }

            })
        return movies
    }

    fun fetchTvShows(): LiveData<List<TvShow>> {
        val tvShows = MutableLiveData<List<TvShow>>()
        apiService.getPopularTvShow(BuildConfig.API_KEY)
            .enqueue(object : Callback<TmdbResponse<TvShow>> {
                override fun onResponse(
                    call: Call<TmdbResponse<TvShow>>,
                    response: Response<TmdbResponse<TvShow>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            tvShows.postValue(it.results)
                        }
                    }
                }

                override fun onFailure(call: Call<TmdbResponse<TvShow>>, t: Throwable) {
                }
            })
        return tvShows
    }
}