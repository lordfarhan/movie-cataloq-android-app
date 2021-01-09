package me.farhan.moviecataloq.core.data.source.remote.network

import me.farhan.moviecataloq.core.BuildConfig.API_KEY
import me.farhan.moviecataloq.core.data.source.remote.response.MovieResponse
import me.farhan.moviecataloq.core.data.source.remote.response.TmdbResponse
import me.farhan.moviecataloq.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author farhan
 * created at at 19:28 on 19/11/20.
 */
interface ApiService {
  @GET("movie/popular")
  suspend fun getPopularMovies(
    @Query("api_key") apiKey: String? = API_KEY,
    @Query("page") page: Int = 1
  ): TmdbResponse<MovieResponse>

  @GET("tv/popular")
  suspend fun getPopularTvShow(
    @Query("api_key") apiKey: String? = API_KEY,
    @Query("page") page: Int = 1
  ): TmdbResponse<TvShowResponse>

  @GET("movie/{movie_id}")
  suspend fun getMovieDetail(
    @Path("movie_id") movieId: Long,
    @Query("api_key") apiKey: String? = API_KEY,
  ): MovieResponse

  @GET("tv/{tv_id}")
  suspend fun getTvShowDetail(
    @Path("tv_id") tvShowId: Long,
    @Query("api_key") apiKey: String? = API_KEY,
  ): TvShowResponse

}