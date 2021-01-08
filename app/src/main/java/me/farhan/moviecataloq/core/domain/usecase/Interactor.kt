package me.farhan.moviecataloq.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.domain.repository.IRepository
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 6:28 on 08/01/21.
 */
class Interactor(private val repository: IRepository) : UseCase {
  override fun getMovies(): Flow<Resource<List<Movie>>> = repository.getMovies()

  override fun getMovieDetail(id: Long): Flow<Resource<Movie>> = repository.getMovieDetail(id)

  override fun getTvShows(): Flow<Resource<List<TvShow>>> = repository.getTvShows()

  override fun getTvShowDetail(id: Long): Flow<Resource<TvShow>> = repository.getTvShowDetail(id)

  override fun getFavoriteMovies(): Flow<List<Movie>> = repository.getFavoriteMovies()

  override fun getFavoriteTvShows(): Flow<List<TvShow>> = repository.getFavoriteTvShows()

  override fun addFavoriteMovie(id: Long) = repository.addFavoriteMovie(id)

  override fun addFavoriteTvShow(id: Long) = repository.addFavoriteTvShow(id)

  override fun deleteFavoriteMovie(id: Long) = repository.deleteFavoriteMovie(id)

  override fun deleteFavoriteTvShow(id: Long) = repository.deleteFavoriteTvShow(id)
}