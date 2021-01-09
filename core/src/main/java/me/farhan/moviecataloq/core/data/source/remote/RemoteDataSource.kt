package me.farhan.moviecataloq.core.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.farhan.moviecataloq.core.BuildConfig
import me.farhan.moviecataloq.core.data.source.remote.network.ApiService
import me.farhan.moviecataloq.core.data.source.remote.response.ApiResponse
import me.farhan.moviecataloq.core.data.source.remote.response.MovieResponse
import me.farhan.moviecataloq.core.data.source.remote.response.TvShowResponse
import me.farhan.moviecataloq.core.util.EspressoIdlingResource

/**
 * @author farhan
 * created at at 22:34 on 14/11/20.
 */
class RemoteDataSource(private val apiService: ApiService) {

  suspend fun fetchMovies(): Flow<ApiResponse<List<MovieResponse>>> {
    EspressoIdlingResource.increment()
    return flow {
      try {
        val response = apiService.getPopularMovies(BuildConfig.API_KEY)
        val data = response.results
        if (data.isNotEmpty()) {
          emit(ApiResponse.Success(data))
        } else {
          emit(ApiResponse.Empty)
        }
      } catch (e: Exception) {
        emit(ApiResponse.Error(e.toString()))
        Log.e("RemoteDataSource", e.toString())
      }
    }.flowOn(Dispatchers.IO)
  }

  fun fetchMovieDetail(id: Long): Flow<ApiResponse<MovieResponse>> {
    EspressoIdlingResource.increment()
    return flow {
      try {
        val response = apiService.getMovieDetail(id, BuildConfig.API_KEY)
        emit(ApiResponse.Success(response))
      } catch (e: Exception) {
        emit(ApiResponse.Error(e.toString()))
        Log.e("RemoteDataSource", e.toString())
      }
    }.flowOn(Dispatchers.IO)
  }

  fun fetchTvShows(): Flow<ApiResponse<List<TvShowResponse>>> {
    EspressoIdlingResource.increment()
    return flow {
      try {
        val response = apiService.getPopularTvShow(BuildConfig.API_KEY)
        val data = response.results
        if (data.isNotEmpty()) {
          emit(ApiResponse.Success(data))
        } else {
          emit(ApiResponse.Empty)
        }
      } catch (e: Exception) {
        emit(ApiResponse.Error(e.toString()))
        Log.e("RemoteDataSource", e.toString())
      }
    }.flowOn(Dispatchers.IO)
  }

  fun fetchTvShowDetail(id: Long): Flow<ApiResponse<TvShowResponse>> {
    EspressoIdlingResource.increment()
    return flow {
      try {
        val response = apiService.getTvShowDetail(id, BuildConfig.API_KEY)
        emit(ApiResponse.Success(response))
      } catch (e: Exception) {
        emit(ApiResponse.Error(e.toString()))
        Log.e("RemoteDataSource", e.toString())
      }
    }.flowOn(Dispatchers.IO)
  }
}