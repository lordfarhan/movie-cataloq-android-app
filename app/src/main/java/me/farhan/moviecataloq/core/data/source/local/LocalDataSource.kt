package me.farhan.moviecataloq.core.data.source.local

import kotlinx.coroutines.flow.Flow
import me.farhan.moviecataloq.core.data.source.local.entity.MovieEntity
import me.farhan.moviecataloq.core.data.source.local.entity.TvShowEntity
import me.farhan.moviecataloq.core.data.source.local.room.MovieCataloqDao

/**
 * @author farhan
 * created at at 11:26 on 28/11/20.
 */
class LocalDataSource constructor(private val dao: MovieCataloqDao) {
  fun getMovies(): Flow<List<MovieEntity>> = dao.getMovies()

  fun getMovieDetail(id: Long): Flow<MovieEntity> = dao.getMovieDetail(id)

  fun getTvShows(): Flow<List<TvShowEntity>> = dao.getTvShows()

  fun getTvShowDetail(id: Long): Flow<TvShowEntity> = dao.getTvShowDetail(id)

  suspend fun insertMovies(movies: List<MovieEntity>) = dao.insertMovies(movies)

  suspend fun insertMovie(movie: MovieEntity) = dao.insertMovie(movie)

  suspend fun insertTvShows(tvShows: List<TvShowEntity>) = dao.insertTvShows(tvShows)

  suspend fun insertTvShow(tvShow: TvShowEntity) = dao.insertTvShow(tvShow)

  fun getFavoriteMovies(): Flow<List<MovieEntity>> = dao.getFavoriteMovies()

  fun getFavoriteTvShows(): Flow<List<TvShowEntity>> = dao.getFavoriteTvShows()

  fun insertFavoriteMovie(id: Long) = dao.insertFavoriteMovie(id)

  fun insertFavoriteTvShow(id: Long) = dao.insertFavoriteTvShow(id)

  fun deleteFavoriteMovie(id: Long) = dao.deleteFavoriteMovie(id)

  fun deleteFavoriteTvShow(id: Long) = dao.deleteFavoriteTvShow(id)
}