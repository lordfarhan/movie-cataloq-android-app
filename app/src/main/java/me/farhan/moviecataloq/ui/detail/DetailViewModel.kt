package me.farhan.moviecataloq.ui.detail

import androidx.lifecycle.*
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow
import me.farhan.moviecataloq.core.domain.usecase.UseCase
import me.farhan.moviecataloq.core.data.Resource

/**
 * @author farhan
 * created at at 11:21 on 02/11/2020.
 */
class DetailViewModel(private val useCase: UseCase) : ViewModel() {
  private var movieId = MutableLiveData<Long>()
  private var tvShowId = MutableLiveData<Long>()

  private lateinit var movie: Movie
  private lateinit var tvShow: TvShow

  fun setMovieId(mMovieId: Long) {
    this.movieId.value = mMovieId
  }

  fun setTvShowId(mTvShowId: Long) {
    this.tvShowId.value = mTvShowId
  }

  fun setMovie(movie: Movie) {
    this.movie = movie
  }

  fun setTvShow(tvShow: TvShow) {
    this.tvShow = tvShow
  }

  fun isFavorite(mIsFavorite: Int? = 0): Boolean {
    return mIsFavorite == 1
  }

  fun getMovie(): LiveData<Resource<Movie>> = Transformations.switchMap(movieId) {
    useCase.getMovieDetail(it).asLiveData()
  }

  fun getTvShow(): LiveData<Resource<TvShow>> = Transformations.switchMap(tvShowId) {
    useCase.getTvShowDetail(it).asLiveData()
  }

  fun addFavorite() {
    movieId.value?.let {
      useCase.addFavoriteMovie(it)
    }
    tvShowId.value?.let {
      useCase.addFavoriteTvShow(it)
    }
  }

  fun removeFavorite() {
    movieId.value?.let {
      useCase.deleteFavoriteMovie(it)
    }
    tvShowId.value?.let {
      useCase.deleteFavoriteTvShow(it)
    }
  }
}