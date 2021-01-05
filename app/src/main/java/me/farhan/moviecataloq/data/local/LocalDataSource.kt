package me.farhan.moviecataloq.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import me.farhan.moviecataloq.data.dao.MovieCataloqDao
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 11:26 on 28/11/20.
 */
class LocalDataSource private constructor(private val dao: MovieCataloqDao) {
  companion object {
    private var INSTANCE: LocalDataSource? = null
    fun getInstance(dao: MovieCataloqDao): LocalDataSource = INSTANCE ?: LocalDataSource(dao)
  }

  fun getMovies(): DataSource.Factory<Int, Movie> = dao.getMovies()

  fun getMovieDetail(id: Long): LiveData<Movie> = dao.getMovieDetail(id)

  fun getTvShows(): DataSource.Factory<Int, TvShow> = dao.getTvShows()

  fun getTvShowDetail(id: Long): LiveData<TvShow> = dao.getTvShowDetail(id)

  fun insertMovies(movies: List<Movie>) = dao.insertMovies(movies)

  fun insertMovie(movie: Movie) = dao.insertMovie(movie)

  fun insertTvShows(tvShows: List<TvShow>) = dao.insertTvShows(tvShows)

  fun insertTvShow(tvShow: TvShow) = dao.insertTvShow(tvShow)

  fun getFavoriteMovies(): DataSource.Factory<Int, Movie> = dao.getFavoriteMovies()

  fun getFavoriteTvShows(): DataSource.Factory<Int, TvShow> = dao.getFavoriteTvShows()

  fun insertFavoriteMovie(id: Long) = dao.insertFavoriteMovie(id)

  fun insertFavoriteTvShow(id: Long) = dao.insertFavoriteTvShow(id)

  fun deleteFavoriteMovie(id: Long) = dao.deleteFavoriteMovie(id)

  fun deleteFavoriteTvShow(id: Long) = dao.deleteFavoriteTvShow(id)
}