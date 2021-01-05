package me.farhan.moviecataloq.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.farhan.moviecataloq.BuildConfig
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.network.ApiService
import me.farhan.moviecataloq.network.response.ApiResponse
import me.farhan.moviecataloq.network.response.TmdbResponse
import me.farhan.moviecataloq.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author farhan
 * created at at 22:34 on 14/11/20.
 */
class RemoteDataSource(private val apiService: ApiService) {
  companion object {
    @Volatile
    private var instance: RemoteDataSource? = null

    fun getInstance(apiService: ApiService): RemoteDataSource =
      instance ?: synchronized(this) {
        instance ?: RemoteDataSource(apiService)
      }
  }

  fun fetchMovies(): LiveData<ApiResponse<List<Movie>>> {
    EspressoIdlingResource.increment()
    val movies = MutableLiveData<ApiResponse<List<Movie>>>()
    apiService.getPopularMovies(BuildConfig.API_KEY)
      .enqueue(object : Callback<TmdbResponse<Movie>> {
        override fun onResponse(
          call: Call<TmdbResponse<Movie>>,
          response: Response<TmdbResponse<Movie>>
        ) {
          if (response.isSuccessful) {
            response.body()?.let {
              movies.value = ApiResponse.success(it.results)
            }
          }
          movies.value = ApiResponse.error(response.message().toString())
          EspressoIdlingResource.decrement()
        }

        override fun onFailure(call: Call<TmdbResponse<Movie>>, t: Throwable) {
          movies.value = ApiResponse.error(t.toString())
          EspressoIdlingResource.decrement()
        }

      })
    return movies
  }

  fun fetchMovieDetail(id: Long): LiveData<ApiResponse<Movie>> {
    EspressoIdlingResource.increment()
    val movie = MutableLiveData<ApiResponse<Movie>>()
    apiService.getMovieDetail(id, BuildConfig.API_KEY)
      .enqueue(object : Callback<Movie> {
        override fun onResponse(
          call: Call<Movie>,
          response: Response<Movie>
        ) {
          if (response.isSuccessful) {
            response.body()?.let {
              movie.value = ApiResponse.success(it)
            }
          }
          movie.value = ApiResponse.error(response.message().toString())
          EspressoIdlingResource.decrement()
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
          movie.value = ApiResponse.error(t.toString())
          EspressoIdlingResource.decrement()
        }

      })
    return movie
  }

  fun fetchTvShows(): LiveData<ApiResponse<List<TvShow>>> {
    EspressoIdlingResource.increment()
    val tvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
    apiService.getPopularTvShow(BuildConfig.API_KEY)
      .enqueue(object : Callback<TmdbResponse<TvShow>> {
        override fun onResponse(
          call: Call<TmdbResponse<TvShow>>,
          response: Response<TmdbResponse<TvShow>>
        ) {
          if (response.isSuccessful) {
            response.body()?.let {
              tvShows.value = ApiResponse.success(it.results)
            }
          }
          tvShows.value = ApiResponse.error(response.message().toString())
          EspressoIdlingResource.decrement()
        }

        override fun onFailure(call: Call<TmdbResponse<TvShow>>, t: Throwable) {
          tvShows.value = ApiResponse.error(t.toString())
          EspressoIdlingResource.decrement()
        }
      })
    return tvShows
  }

  fun fetchTvShowDetail(id: Long): LiveData<ApiResponse<TvShow>> {
    EspressoIdlingResource.increment()
    val tvShow = MutableLiveData<ApiResponse<TvShow>>()
    apiService.getTvShowDetail(id, BuildConfig.API_KEY)
      .enqueue(object : Callback<TvShow> {
        override fun onResponse(
          call: Call<TvShow>,
          response: Response<TvShow>
        ) {
          if (response.isSuccessful) {
            response.body()?.let {
              tvShow.value = ApiResponse.success(it)
            }
          }
          tvShow.value = ApiResponse.error(response.message().toString())
          EspressoIdlingResource.decrement()
        }

        override fun onFailure(call: Call<TvShow>, t: Throwable) {
          tvShow.value = ApiResponse.error(t.toString())
          EspressoIdlingResource.decrement()
        }
      })
    return tvShow
  }
}