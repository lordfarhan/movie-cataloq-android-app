package me.farhan.moviecataloq.core.domain.repository

import kotlinx.coroutines.flow.Flow
import me.farhan.moviecataloq.core.data.Resource
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow

/**
 * @author farhan
 * created at at 7:58 on 08/01/21.
 */
interface IRepository {
  fun getMovies(): Flow<Resource<List<Movie>>>
  fun getMovieDetail(id: Long): Flow<Resource<Movie>>
  fun getTvShows(): Flow<Resource<List<TvShow>>>
  fun getTvShowDetail(id: Long): Flow<Resource<TvShow>>
  fun getFavoriteMovies(): Flow<List<Movie>>
  fun getFavoriteTvShows(): Flow<List<TvShow>>
  fun addFavoriteMovie(id: Long)
  fun addFavoriteTvShow(id: Long)
  fun deleteFavoriteMovie(id: Long)
  fun deleteFavoriteTvShow(id: Long)
}