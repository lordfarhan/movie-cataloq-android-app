package me.farhan.moviecataloq.network

import me.farhan.moviecataloq.BuildConfig.API_KEY
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.network.response.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author farhan
 * created at at 19:28 on 19/11/20.
 */
interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 1
    ): Call<TmdbResponse<Movie>>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 1
    ): Call<TmdbResponse<TvShow>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String? = API_KEY,
    ): Call<Movie>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") tvShowId: Long,
        @Query("api_key") apiKey: String? = API_KEY,
    ): Call<TvShow>

}