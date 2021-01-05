package me.farhan.moviecataloq.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 11:21 on 02/11/2020.
 */
class DetailViewModel(private val repository: MovieCataloqRepository) : ViewModel() {
  private var movieId = MutableLiveData<Long>()
  private var tvShowId = MutableLiveData<Long>()

  private lateinit var movie: Movie
  private lateinit var tvShow: TvShow

  fun setMovieId(mMovieId: Long) {
    this.movieId.value = mMovieId
  }

  fun getMovieId(): Long? = this.movieId.value

  fun setTvShowId(mTvShowId: Long) {
    this.tvShowId.value = mTvShowId
  }

  fun getTvShowId(): Long? = this.tvShowId.value

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
    repository.getMovieDetail(it)
  }

  fun getTvShow(): LiveData<Resource<TvShow>> = Transformations.switchMap(tvShowId) {
    repository.getTvShowDetail(it)
  }

  fun addFavorite() {
    movieId.value?.let {
      repository.addFavoriteMovie(it)
    }
    tvShowId.value?.let {
      repository.addFavoriteTvShow(it)
    }
  }

  fun removeFavorite() {
    movieId.value?.let {
      repository.deleteFavoriteMovie(it)
    }
    tvShowId.value?.let {
      repository.deleteFavoriteTvShow(it)
    }
  }
}